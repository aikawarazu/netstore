package com.fc.controller;

import com.fc.common.model.PageResult;
import com.fc.controller.bean.BookInfoEditVO;
import com.fc.controller.bean.BookInfoVO;
import com.fc.controller.validator.BookInfoValidator;
import com.fc.model.BookInfo;
import com.fc.model.Category;
import com.fc.service.BookService;
import com.fc.service.CategoryService;
import com.fc.service.impl.BaseService;
import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Longs;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("manage/book")
public class BookController extends BaseService {

  private final BookService bookService;

  private final CategoryService categoryService;

  @Autowired
  public BookController(BookService bookService, CategoryService categoryService, BookInfoValidator bookInfoValidator) {
    this.bookService = bookService;
    this.categoryService = categoryService;
    this.bookInfoValidator = bookInfoValidator;
  }

  @RequestMapping("list")
  public ModelAndView bookList(
      @RequestParam(required = false) Long categoryId,
      @RequestParam(required = false, defaultValue = "1") Integer page,
      @RequestParam(required = false, defaultValue = "3") Integer pageSize) {
    List<Category> categoryList = categoryService.list();
    if (categoryId != null) {
      final long categoryIdTmp = categoryId;
      if (categoryList.stream().mapToLong(Category::getId).noneMatch(value -> value == categoryIdTmp)) {
        categoryId = null;
      }
    }
    PageResult<BookInfo> pageResult = bookService.list(page, pageSize, categoryId);
    List<BookInfo> list = pageResult.getData();
    List<Long> categoryIds = list.stream().map(BookInfo::getCategoryId).collect(Collectors.toList());
    List<Category> categories = categoryService.findByIds(categoryIds);
    final Map<Long, Category> categoryMap = categories.stream().collect(Collectors.toMap(Category::getId, a -> a));
    List<BookInfoVO> bookInfos = list.parallelStream().map(bookInfo -> {
      DecimalFormat decimalFormat = new DecimalFormat("0.00");
      Category category = categoryMap.get(bookInfo.getCategoryId());
      String name = null;
      if (category != null) {
        name = category.getName();
      }
      return new BookInfoVO(bookInfo, name, decimalFormat.format(bookInfo.getPrice()));
    }).collect(Collectors.toList());
    ModelAndView modelAndView = new ModelAndView("manage/listBook");
    PageResult<BookInfoVO> result = new PageResult<>(pageResult.getPageSize(), pageResult.getTotalCount());
    result.setCurPage(pageResult.getCurPage());
    result.setData(bookInfos);
    modelAndView.addObject("pageResult", result);
    modelAndView.addObject("cList", categoryList);
    modelAndView.addObject("categoryId", categoryId);

    return modelAndView;
  }

  @GetMapping("add")
  public String addBookPage(Model model) {
    List<Category> cList = categoryService.list();
    model.addAttribute("cList", cList);
    return "manage/addBook";
  }

  private final BookInfoValidator bookInfoValidator;

  /**
   * 注解里的 bookInfoEditVO 参数，要和接口里面的参数名称相同
   */
  @InitBinder("bookInfoEditVO")
  protected void initBind(WebDataBinder webDataBinder) {
    webDataBinder.setValidator(bookInfoValidator);
  }

  @PostMapping("add")
  @ResponseBody
  public ModelMap add(@Validated BookInfoEditVO bookInfoEditVO, BindingResult bindingResult) {
    List<FieldError> fieldErrors = bindingResult.getFieldErrors();
    if (!fieldErrors.isEmpty()) {
      ModelMap modelMap = new ModelMap();
      modelMap.addAttribute("fieldErrors", fieldErrors.parallelStream().map(ErrorMessage::new).collect(Collectors.toList()));
      return modelMap;
    }
    BookInfo bookInfo = new BookInfo();
    bookInfo.setBookName(bookInfoEditVO.getBookName());
    bookInfo.setBookAuthor(bookInfoEditVO.getBookAuthor());
    bookInfo.setPrice(Doubles.tryParse(bookInfoEditVO.getPrice()));
    bookInfo.setBookVersion(bookInfoEditVO.getBookVersion());
    bookInfo.setPublisherName(bookInfoEditVO.getPublisher());
    bookInfo.setCategoryId(bookInfoEditVO.getCategoryId() == null ? null : Longs.tryParse(bookInfoEditVO.getCategoryId()));
    bookInfo.setDescription(bookInfoEditVO.getDescription());
    try {
      bookInfo.setPic(savePic(bookInfoEditVO.getFile1(),"static"));
      bookInfo.setPicture2(savePic(bookInfoEditVO.getFile2(),"WEB-INF"));
      bookService.save(bookInfo);
    } catch (IOException e) {
      logger.error("", e);
      ModelMap modelMap = new ModelMap();
      List<ErrorMessage> fileErrors = Lists.newArrayList(new ErrorMessage("IO error when read file"));
      modelMap.addAttribute("fieldErrors", fileErrors);
      return modelMap;
    } catch (Exception e) {
      logger.error("", e);
      ModelMap modelMap = new ModelMap();
      List<ErrorMessage> fileErrors = Lists.newArrayList(new ErrorMessage("#server error!!"));
      modelMap.addAttribute("fieldErrors", fileErrors);
      return modelMap;
    }

    ModelMap modelMap = new ModelMap();
    modelMap.addAttribute("redirect", "add");
    return modelMap;
  }

  public static class ErrorMessage implements Serializable {

    private String defaultMessage;

    ErrorMessage(String defaultMessage) {
      this.defaultMessage = defaultMessage;
    }

    ErrorMessage(FieldError fieldError) {
      defaultMessage = fieldError.getDefaultMessage();
    }

    public String getDefaultMessage() {
      return defaultMessage;
    }
  }

  private String getFileName(String userId, String fileName) {
    return userId + File.pathSeparator + fileName;
  }

  private Long getCategoryId(String categoryName) {
    Category byName = categoryService.findByName(categoryName);
    if (byName == null) {
      return 0L;
    }
    return byName.getId();
  }

  private String savePic(MultipartFile multipartFile,String resRootPath) throws IOException {
    if (multipartFile == null) {
      return null;
    }
    String filename = multipartFile.getOriginalFilename();
    if (StringUtils.isEmpty(filename)) {
      return null;
    }
    String rootPath = getClass().getResource("/").getPath();
    String fullFileName = getFileName(Integer.toHexString(random.nextInt()) + "@@@" + filename);
    File file = new File(rootPath, resRootPath + File.separator + "images" + File.separator + fullFileName);
    logger.debug("filename:" + filename);
    logger.debug("save 2:" + file.getAbsolutePath());
    if (!file.getParentFile().exists()) {
      boolean mkdirs = file.getParentFile().mkdirs();
      logger.debug("mkdirs:{}", mkdirs);
    }
//    file.isAbsolute()//true

    multipartFile.transferTo(file);
//    try (FileOutputStream fileOutputStream = new FileOutputStream(file);
//        InputStream inputStream = multipartFile.getInputStream()
//    ) {
//      byte[] bytes = new byte[inputStream.available()];
//      int read = inputStream.read(bytes);
//      if (read > 0) {
//        fileOutputStream.write(bytes);
//      }
//      fileOutputStream.flush();
//    }
    return fullFileName;
  }

  private String getFileName(String fullName) {
    int hashCode = fullName.hashCode();
    return "uploads"
        + File.separator + Integer.toHexString((hashCode >> 24 & 0xff))
        + File.separator + Integer.toHexString((hashCode >> 16 & 0xff))
        + File.separator + Integer.toHexString((hashCode >> 8 & 0xff))
        + File.separator + Integer.toHexString((hashCode & 0xff))
        + File.separator + fullName;
  }

  private Random random = new SecureRandom();

  @PostMapping(value = "del")
  @ResponseBody
  public ModelMap del(@RequestBody JSONObject jsonObj) {
    Number id = jsonObj.getAsNumber("id");
    ModelMap modelMap = new ModelMap();
    bookService.deleteById(id.intValue());
    return modelMap;
  }


}
