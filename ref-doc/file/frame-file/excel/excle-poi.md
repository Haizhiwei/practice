# java实现excel的导入导出（poi详解）

关于apache的excel表格的操作有两个jar包,poi和poi-ooxml,他们的主要分别是:

poi处理excel2003,即*.xls文件

poi-ooxml可以处理excel2007+,即*.xlsx文件

```java
// 一个excel表格：
HSSFWorkbook wb = new HSSFWorkbook(); //EXCLE_2003 = "xls";
XSSFWorkbook wb = new XSSFWorkbook // EXCLE_2007 = "xlsx"
// 一个工作表格（sheet）：
HSSFSheet sheet = wb.createSheet(sheetName);//EXCLE_2003 = "xls";
XSSFSheet sheet = wb.createSheet(sheetName);//EXCLE_2007 = "xlsx"
// 一行（row）：
HSSFRow row = sheet.createRow(0);
XSSFRow row = sheet.createRow(0);

// 一个单元格（cell）,并设置单元格格式
HSSFCell cell = row.createCell((short)0)    	//EXCLE_2003 = "xls";
HSSFCellStyle style = wb.createCellStyle()
 
XSSFCellStyle style = wb.createCellStyle();		//EXCLE_2007 = "xlsx"
style.setAlignment(HorizontalAlignment.CENTER);
  
// 单元格内容格式（）
HSSFDataFormat format= wb.createDataFormat();

```



## e.g(excle2007):##



```java
/**
 * 处理excel读入的工具类
 * Created by Liujishuai on 2015/8/5.
 */
public class ExcelUtils {
    /**
     * 要求excel版本在2007以上
     *
     * @param file 文件信息
     * @return
     * @throws Exception
     */
    public static List<List<Object>> readExcel(File file) throws Exception {
        if(!file.exists()){
            throw new Exception("找不到文件");
        }
        List<List<Object>> list = new LinkedList<List<Object>>();
        XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
        // 读取第一张表格内容
        XSSFSheet sheet = xwb.getSheetAt(0);
        XSSFRow row = null;
        XSSFCell cell = null;
        for (int i = (sheet.getFirstRowNum() + 1); i <= (sheet.getPhysicalNumberOfRows() - 1); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<Object> linked = new LinkedList<Object>();
            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                Object value = null;
                cell = row.getCell(j);
                if (cell == null) {
                    continue;
                }
                switch (cell.getCellType()) {
                    case XSSFCell.CELL_TYPE_STRING:
                        //String类型返回String数据
                        value = cell.getStringCellValue();
                        break;
                    case XSSFCell.CELL_TYPE_NUMERIC:
          //日期数据返回LONG类型的时间戳
  if ("yyyy\"年\"m\"月\"d\"日\";@".equals(cell.getCellStyle().getDataFormatString())) {
    //System.out.println(cell.getNumericCellValue()+":
    // 日期格式："+cell.getCellStyle().getDataFormatString());
              value = 
               DateUtils.getMillis(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())) / 1000;
} else {
         //数值类型返回double类型的数字
  		// System.out.println(cell.getNumericCellValue()+":
       //  格式："+cell.getCellStyle().getDataFormatString());
            value = cell.getNumericCellValue();
  }
                       break;
                    case XSSFCell.CELL_TYPE_BOOLEAN:
                        //布尔类型
                        value = cell.getBooleanCellValue();
                        break;
                    case XSSFCell.CELL_TYPE_BLANK:
                        //空单元格
                        break;
                    default:
                        value = cell.toString();
                }
                if (value != null && !value.equals("")) {
                    //单元格不为空，则加入列表
                    linked.add(value);
                }
            }
            if (linked.size()!= 0) {
                list.add(linked);
            }
        }
        return list;
    }
    
 
    /**
     * 导出excel
     * @param excel_name 导出的excel路径（需要带.xlsx)
     * @param headList  excel的标题备注名称
     * @param fieldList excel的标题字段（与数据中map中键值对应）
     * @param dataList  excel数据
     * @throws Exception
     */
    public static void createExcel(String excel_name, String[] headList,
                                   String[] fieldList, List<Map<String, Object>> dataList)
            throws Exception {
        // 创建新的Excel 工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 在Excel工作簿中建一工作表，其名为缺省值
        XSSFSheet sheet = workbook.createSheet();
        // 在索引0的位置创建行（最顶端的行）
        XSSFRow row = sheet.createRow(0);
        // 设置excel头（第一行）的头名称
        for (int i = 0; i < headList.length; i++) {
 
            // 在索引0的位置创建单元格（左上端）
            XSSFCell cell = row.createCell(i);
            // 定义单元格为字符串类型
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            // 在单元格中输入一些内容
            cell.setCellValue(headList[i]);
        }
        // ===============================================================
        //添加数据
        for (int n = 0; n < dataList.size(); n++) {
            // 在索引1的位置创建行（最顶端的行）
            XSSFRow row_value = sheet.createRow(n + 1);
            Map<String, Object> dataMap = dataList.get(n);
            // ===============================================================
            for (int i = 0; i < fieldList.length; i++) {
 
                // 在索引0的位置创建单元格（左上端）
                XSSFCell cell = row_value.createCell(i);
                // 定义单元格为字符串类型
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                // 在单元格中输入一些内容
                cell.setCellValue((dataMap.get(fieldList[i])).toString());
            }
            // ===============================================================
        }
        // 新建一输出文件流
        FileOutputStream fos = new FileOutputStream(excel_name);
        // 把相应的Excel 工作簿存盘
        workbook.write(fos);
        fos.flush();
        // 操作结束，关闭文件
        fos.close();
    }
}
```

## 二##

###1.1注解类###

```java

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {

    /**
     * 导出字段名（默认调用当前字段的“get”方法，
     * 如指定导出字段为对象，请填写“对象名.对象属性”，例：“area.name”、“office.name”）
     */
    String value() default "";
    
    /**
     * 导出字段标题（需要添加批注请用“**”分隔，标题**批注，仅对导出模板有效）
     */
    String title();
    
    /**
     * 字段类型（0：导出导入；1：仅导出；2：仅导入）
     */
    int type() default 0;

    /**
     * 导出字段对齐方式（0：自动；1：靠左；2：居中；3：靠右）
     */
    int align() default 0;
    
    /**
     * 导出字段字段排序（升序）
     */
    int sort() default 0;

    /**
     * 如果是字典类型，请设置字典的type值
     */
    String dictType() default "";
    
    /**
     * 反射类型
     */
    Class<?> fieldType() default Class.class;
    
    /**
     * 字段归属组（根据分组导出导入）
     */
    int[] groups() default {};
}
```

### 1.2导出Excel类###

```java
public class ExportExcel {
    
    private static Logger log = LoggerFactory.getLogger(ExportExcel.class);
            
    /**
     * 工作薄对象
     */
    private SXSSFWorkbook wb;
    
    /**
     * 工作表对象
     */
    private Sheet sheet;
    
    /**
     * 样式列表
     */
    private Map<String, CellStyle> styles;
    
    /**
     * 当前行号
     */
    private int rownum;
    
    /**
     * 注解列表（Object[]{ ExcelField, Field/Method }）
     */
    List<Object[]> annotationList = Lists.newArrayList();
    
    /**
     * 构造函数
     * @param title 表格标题，传“空值”，表示无标题
     * @param cls 实体对象，通过annotation.ExportField获取标题
     */
    public ExportExcel(String title, Class<?> cls){
        this(title, cls, 1);
    }
    
    /**
     * 构造函数
     * @param title 表格标题，传“空值”，表示无标题
     * @param cls 实体对象，通过annotation.ExportField获取标题
     * @param type 导出类型（1:导出数据；2：导出模板）
     * @param groups 导入分组
     */
    public ExportExcel(String title, Class<?> cls, int type, int... groups){
        // Get annotation field 
		Field[] fs = cls.getDeclaredFields();
        for (Field f : fs){
            ExcelField ef = f.getAnnotation(ExcelField.class);
            if (ef != null && (ef.type()==0 || ef.type()==type)){
                if (groups!=null && groups.length>0){
                    boolean inGroup = false;
                    for (int g : groups){
                        if (inGroup){
                            break;
                        }
                        for (int efg : ef.groups()){
                            if (g == efg){
                                inGroup = true;
                                annotationList.add(new Object[]{ef, f});
                                break;
                            }
                        }
                    }
                }else{
                    annotationList.add(new Object[]{ef, f});
                }
            }
        }
        // Get annotation method
        Method[] ms = cls.getDeclaredMethods();
        for (Method m : ms){
            ExcelField ef = m.getAnnotation(ExcelField.class);
            if (ef != null && (ef.type()==0 || ef.type()==type)){
                if (groups!=null && groups.length>0){
                    boolean inGroup = false;
                    for (int g : groups){
                        if (inGroup){
                            break;
                        }
                        for (int efg : ef.groups()){
                            if (g == efg){
                                inGroup = true;
                                annotationList.add(new Object[]{ef, m});
                                break;
                            }
                        }
                    }
                }else{
                    annotationList.add(new Object[]{ef, m});
                }
            }
        }
        // Field sorting
        Collections.sort(annotationList, new Comparator<Object[]>() {
            public int compare(Object[] o1, Object[] o2) {
                return new Integer(((ExcelField)o1[0]).sort()).compareTo(
                        new Integer(((ExcelField)o2[0]).sort()));
            };
        });
        // Initialize
        List<String> headerList = Lists.newArrayList();
        for (Object[] os : annotationList){
            String t = ((ExcelField)os[0]).title();
            // 如果是导出，则去掉注释
            if (type==1){
                String[] ss = StringUtils.split(t, "**", 2);
                if (ss.length==2){
                    t = ss[0];
                }
            }
            headerList.add(t);
        }
        initialize(title, headerList);
    }
    
    /**
     * 构造函数
     * @param title 表格标题，传“空值”，表示无标题
     * @param headers 表头数组
     */
    public ExportExcel(String title, String[] headers) {
        initialize(title, Lists.newArrayList(headers));
    }
    
    /**
     * 构造函数
     * @param title 表格标题，传“空值”，表示无标题
     * @param headerList 表头列表
     */
    public ExportExcel(String title, List<String> headerList) {
        initialize(title, headerList);
    }
    
    /**
     * 初始化函数
     * @param title 表格标题，传“空值”，表示无标题
     * @param headerList 表头列表
     */
    private void initialize(String title, List<String> headerList) {
        this.wb = new SXSSFWorkbook(500);
        this.sheet = wb.createSheet("Export");
        this.styles = createStyles(wb);
        // Create title
        if (StringUtils.isNotBlank(title)){
            Row titleRow = sheet.createRow(rownum++);
            titleRow.setHeightInPoints(30);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellStyle(styles.get("title"));
            titleCell.setCellValue(title);
            sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),
                    titleRow.getRowNum(), titleRow.getRowNum(), headerList.size()-1));
        }
        // Create header
        if (headerList == null){
            throw new RuntimeException("headerList not null!");
        }
        Row headerRow = sheet.createRow(rownum++);
        headerRow.setHeightInPoints(16);
        for (int i = 0; i < headerList.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellStyle(styles.get("header"));
            String[] ss = StringUtils.split(headerList.get(i), "**", 2);
            if (ss.length==2){
                cell.setCellValue(ss[0]);
                Comment comment = this.sheet.createDrawingPatriarch().createCellComment(
                        new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
                comment.setString(new XSSFRichTextString(ss[1]));
                cell.setCellComment(comment);
            }else{
                cell.setCellValue(headerList.get(i));
            }
            sheet.autoSizeColumn(i);
        }
        for (int i = 0; i < headerList.size(); i++) {  
            int colWidth = sheet.getColumnWidth(i)*2;
            sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);  
        }
        log.debug("Initialize success.");
    }
    
    /**
     * 创建表格样式
     * @param wb 工作薄对象
     * @return 样式列表
     */
    private Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        
        CellStyle style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        Font titleFont = wb.createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(titleFont);
        styles.put("title", style);

        style = wb.createCellStyle();
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        Font dataFont = wb.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 10);
        style.setFont(dataFont);
        styles.put("data", style);
        
        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(CellStyle.ALIGN_LEFT);
        styles.put("data1", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(CellStyle.ALIGN_CENTER);
        styles.put("data2", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        styles.put("data3", style);
        
        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
//        style.setWrapText(true);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        Font headerFont = wb.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(headerFont);
        styles.put("header", style);
        
        return styles;
    }
  /**
     * 添加一行
     * @return 行对象
     */
    public Row addRow(){
        return sheet.createRow(rownum++);
    }
    

    /**
     * 添加一个单元格
     * @param row 添加的行
     * @param column 添加列号
     * @param val 添加值
     * @return 单元格对象
     */
    public Cell addCell(Row row, int column, Object val){
        return this.addCell(row, column, val, 0, Class.class);
    }
    
    /**
     * 添加一个单元格
     * @param row 添加的行
     * @param column 添加列号
     * @param val 添加值
     * @param align 对齐方式（1：靠左；2：居中；3：靠右）
     * @return 单元格对象
     */
    public Cell addCell(Row row, int column, Object val, int align, Class<?> fieldType){
        Cell cell = row.createCell(column);
        String cellFormatString = "@";
        try {
            if(val == null){
                cell.setCellValue("");
            }else if(fieldType != Class.class){
                cell.setCellValue((String)fieldType.getMethod("setValue", Object.class).invoke(null, val));
            }else{
                if(val instanceof String) {
                    cell.setCellValue((String) val);
                }else if(val instanceof Integer) {
                    cell.setCellValue((Integer) val);
                    cellFormatString = "0";
                }else if(val instanceof Long) {
                    cell.setCellValue((Long) val);
                    cellFormatString = "0";
                }else if(val instanceof Double) {
                    cell.setCellValue((Double) val);
                    cellFormatString = "0.00";
                }else if(val instanceof Float) {
                    cell.setCellValue((Float) val);
                    cellFormatString = "0.00";
                }else if(val instanceof Date) {
                    cell.setCellValue((Date) val);
                    cellFormatString = "yyyy-MM-dd HH:mm";
                }else {
                    cell.setCellValue((String)Class.forName(this.getClass().getName().replaceAll(this.getClass().getSimpleName(), 
                        "fieldtype."+val.getClass().getSimpleName()+"Type")).getMethod("setValue", Object.class).invoke(null, val));
                }
            }
            if (val != null){
                CellStyle style = styles.get("data_column_"+column);
                if (style == null){
                    style = wb.createCellStyle();
                    style.cloneStyleFrom(styles.get("data"+(align>=1&&align<=3?align:"")));
                    style.setDataFormat(wb.createDataFormat().getFormat(cellFormatString));
                    styles.put("data_column_" + column, style);
                }
                cell.setCellStyle(style);
            }
        } catch (Exception ex) {
            log.info("Set cell value ["+row.getRowNum()+","+column+"] error: " + ex.toString());
            cell.setCellValue(val.toString());
        }
        return cell;
    }

    /**
     * 添加数据（通过annotation.ExportField添加数据）
     * @return list 数据列表
     */
    public <E> ExportExcel setDataList(List<E> list){
        for (E e : list){
            int colunm = 0;
            Row row = this.addRow();
            StringBuilder sb = new StringBuilder();
            for (Object[] os : annotationList){
                ExcelField ef = (ExcelField)os[0];
                Object val = null;
                // Get entity value
                try{
                    if (StringUtils.isNotBlank(ef.value())){
                        val = Reflections.invokeGetter(e, ef.value());
                    }else{
                        if (os[1] instanceof Field){
                            val = Reflections.invokeGetter(e, ((Field)os[1]).getName());
                        }else if (os[1] instanceof Method){
                            val = Reflections.invokeMethod(e, ((Method)os[1]).getName(), new Class[] {}, new Object[] {});
                        }
                    }
                    // If is dict, get dict label
                    if (StringUtils.isNotBlank(ef.dictType())){
                        val = DictUtils.getDictLabel(val==null?"":val.toString(), ef.dictType(), "");
                    }
                }catch(Exception ex) {
                    // Failure to ignore
                    log.info(ex.toString());
                    val = "";
                }
                this.addCell(row, colunm++, val, ef.align(), ef.fieldType());
                sb.append(val + ", ");
            }
            log.debug("Write success: ["+row.getRowNum()+"] "+sb.toString());
        }
        return this;
    }
    
    /**
     * 输出数据流
     * @param os 输出数据流
     */
    public ExportExcel write(OutputStream os) throws IOException{
        wb.write(os);
        return this;
    }
    
    /**
     * 输出到客户端
     * @param fileName 输出文件名
     */
    public ExportExcel write(HttpServletResponse response, String fileName ,HttpServletRequest request) throws IOException{
        final String userAgent = request.getHeader("USER-AGENT");
        response.reset();
        String finalFileName = null;
        if (StringUtils.contains(userAgent, "MSIE")) {// IE浏览器
            finalFileName = URLEncoder.encode(fileName, "UTF8");
        } else if (StringUtils.contains(userAgent, "Mozilla")) {// google,火狐浏览器
            finalFileName = new String(fileName.getBytes(), "ISO8859-1");
        } else {
            finalFileName = URLEncoder.encode(fileName, "UTF8");// 其他浏览器
        }
        response.setHeader("Content-Disposition", "attachment; filename=\"" + finalFileName + "\"");// 这里设置一下让浏览器弹出下载提示框，而不是直接在浏览器中打开
        response.setContentType("application/vnd.ms-excel");
        write(response.getOutputStream());
        return this;
    }
    
    /**
     * 输出到文件
     * @param fileName 输出文件名
     */
    public ExportExcel writeFile(String name) throws FileNotFoundException, IOException{
        FileOutputStream os = new FileOutputStream(name);
        this.write(os);
        return this;
    }
    
    /**
     * 清理临时文件
     */
    public ExportExcel dispose(){
        wb.dispose();
        return this;
    }


```

### 1.3.导入Excel类

```java
public class ImportExcel {
    
    private static Logger log = LoggerFactory.getLogger(ImportExcel.class);
   /**
     * 工作薄对象
     */
    private Workbook wb;
    
    /**
     * 工作表对象
     */
    private Sheet sheet;
    
    /**
     * 标题行号
     */
    private int headerNum;
    
    /**
     * 构造函数
     * @param path 导入文件，读取第一个工作表
     * @param headerNum 标题行号，数据行号=标题行号+1
     * @throws InvalidFormatException 
     * @throws IOException 
     */
    public ImportExcel(String fileName, int headerNum) 
            throws InvalidFormatException, IOException {
        this(new File(fileName), headerNum);
    }
    
    /**
     * 构造函数
     * @param path 导入文件对象，读取第一个工作表
     * @param headerNum 标题行号，数据行号=标题行号+1
     * @throws InvalidFormatException 
     * @throws IOException 
     */
    public ImportExcel(File file, int headerNum) 
            throws InvalidFormatException, IOException {
        this(file, headerNum, 0);
    }

    /**
     * 构造函数
     * @param path 导入文件
     * @param headerNum 标题行号，数据行号=标题行号+1
     * @param sheetIndex 工作表编号
     * @throws InvalidFormatException 
     * @throws IOException 
     */
    public ImportExcel(String fileName, int headerNum, int sheetIndex) 
            throws InvalidFormatException, IOException {
        this(new File(fileName), headerNum, sheetIndex);
    }
    
    /**
     * 构造函数
     * @param path 导入文件对象
     * @param headerNum 标题行号，数据行号=标题行号+1
     * @param sheetIndex 工作表编号
     * @throws InvalidFormatException 
     * @throws IOException 
     */
    public ImportExcel(File file, int headerNum, int sheetIndex) 
            throws InvalidFormatException, IOException {
        this(file.getName(), new FileInputStream(file), headerNum, sheetIndex);
    }
    
    /**
     * 构造函数
     * @param file 导入文件对象
     * @param headerNum 标题行号，数据行号=标题行号+1
     * @param sheetIndex 工作表编号
     * @throws InvalidFormatException 
     * @throws IOException 
     */
    public ImportExcel(MultipartFile multipartFile, int headerNum, int sheetIndex) 
            throws InvalidFormatException, IOException {
        this(multipartFile.getOriginalFilename(), multipartFile.getInputStream(), headerNum, sheetIndex);
    }

    /**
     * 构造函数
     * @param path 导入文件对象
     * @param headerNum 标题行号，数据行号=标题行号+1
     * @param sheetIndex 工作表编号
     * @throws InvalidFormatException 
     * @throws IOException 
     */
    public ImportExcel(String fileName, InputStream is, int headerNum, int sheetIndex) 
            throws InvalidFormatException, IOException {
        if (StringUtils.isBlank(fileName)){
            throw new RuntimeException("导入文档为空!");
        }else if(fileName.toLowerCase().endsWith("xls")){    
            this.wb = new HSSFWorkbook(is);    
        }else if(fileName.toLowerCase().endsWith("xlsx")){  
            this.wb = new XSSFWorkbook(is);
        }else{  
            throw new RuntimeException("文档格式不正确!");
        }  
        if (this.wb.getNumberOfSheets()<sheetIndex){
            throw new RuntimeException("文档中没有工作表!");
        }
        this.sheet = this.wb.getSheetAt(sheetIndex);
        this.headerNum = headerNum;
        log.debug("Initialize success.");
    }
    
    /**
     * 获取行对象
     * @param rownum
     * @return
     */
    public Row getRow(int rownum){
        return this.sheet.getRow(rownum);
    }

    /**
     * 获取数据行号
     * @return
     */
    public int getDataRowNum(){
        return headerNum+1;
    }
    
    /**
     * 获取最后一个数据行号
     * @return
     */
    public int getLastDataRowNum(){
        return this.sheet.getLastRowNum()+headerNum;
    }
    
    /**
     * 获取最后一个列号
     * @return
     */
    public int getLastCellNum(){
        return this.getRow(headerNum).getLastCellNum();
    }
    
    /**
     * 获取单元格值
     * @param row 获取的行
     * @param column 获取单元格列号
     * @return 单元格值
     */
    public Object getCellValue(Row row, int column){
        Object val = "";
        try{
            Cell cell = row.getCell(column);
            if (cell != null){
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                    val = cell.getNumericCellValue();
                }else if (cell.getCellType() == Cell.CELL_TYPE_STRING){
                    val = cell.getStringCellValue();
                }else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA){
                    val = cell.getCellFormula();
                }else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){
                    val = cell.getBooleanCellValue();
                }else if (cell.getCellType() == Cell.CELL_TYPE_ERROR){
                    val = cell.getErrorCellValue();
                }
            }
        }catch (Exception e) {
            return val;
        }
        return val;
    }
    
    /**
     * 获取导入数据列表
     * @param cls 导入对象类型
     * @param groups 导入分组
     */
    public <E> List<E> getDataList(Class<E> cls, int... groups) throws InstantiationException, IllegalAccessException{
        List<Object[]> annotationList = Lists.newArrayList();
        // Get annotation field 
        Field[] fs = cls.getDeclaredFields();
        for (Field f : fs){
            ExcelField ef = f.getAnnotation(ExcelField.class);
            if (ef != null && (ef.type()==0 || ef.type()==2)){
                if (groups!=null && groups.length>0){
                    boolean inGroup = false;
                    for (int g : groups){
                        if (inGroup){
                            break;
                        }
                        for (int efg : ef.groups()){
                            if (g == efg){
                                inGroup = true;
                                annotationList.add(new Object[]{ef, f});
                                break;
                            }
                        }
                    }
                }else{
                    annotationList.add(new Object[]{ef, f});
                }
            }
        }
        // Get annotation method
        Method[] ms = cls.getDeclaredMethods();
        for (Method m : ms){
            ExcelField ef = m.getAnnotation(ExcelField.class);
            if (ef != null && (ef.type()==0 || ef.type()==2)){
                if (groups!=null && groups.length>0){
                    boolean inGroup = false;
                    for (int g : groups){
                        if (inGroup){
                            break;
                        }
                        for (int efg : ef.groups()){
                            if (g == efg){
                                inGroup = true;
                                annotationList.add(new Object[]{ef, m});
                                break;
                            }
                        }
                    }
                }else{
                    annotationList.add(new Object[]{ef, m});
                }
            }
        }
        // Field sorting
        Collections.sort(annotationList, new Comparator<Object[]>() {
            public int compare(Object[] o1, Object[] o2) {
                return new Integer(((ExcelField)o1[0]).sort()).compareTo(
                        new Integer(((ExcelField)o2[0]).sort()));
            };
        });
        //log.debug("Import column count:"+annotationList.size());
        // Get excel data
        List<E> dataList = Lists.newArrayList();
        for (int i = this.getDataRowNum(); i < this.getLastDataRowNum(); i++) {
            E e = (E)cls.newInstance();
            int column = 0;
            Row row = this.getRow(i);
            StringBuilder sb = new StringBuilder();
            for (Object[] os : annotationList){
                Object val = this.getCellValue(row, column++);
                if (val != null){
                    ExcelField ef = (ExcelField)os[0];
                    // If is dict type, get dict value
                    if (StringUtils.isNotBlank(ef.dictType())){
                        val = DictUtils.getDictValue(val.toString(), ef.dictType(), "");
                        //log.debug("Dictionary type value: ["+i+","+colunm+"] " + val);
                    }
                    // Get param type and type cast
                    Class<?> valType = Class.class;
                    if (os[1] instanceof Field){
                        valType = ((Field)os[1]).getType();
                    }else if (os[1] instanceof Method){
                        Method method = ((Method)os[1]);
                        if ("get".equals(method.getName().substring(0, 3))){
                            valType = method.getReturnType();
                        }else if("set".equals(method.getName().substring(0, 3))){
                            valType = ((Method)os[1]).getParameterTypes()[0];
                        }
                    }
                    //log.debug("Import value type: ["+i+","+column+"] " + valType);
                    try {
                        if (valType == String.class){
                            String s = String.valueOf(val.toString());
                            if(StringUtils.endsWith(s, ".0")){
                                val = StringUtils.substringBefore(s, ".0");
                            }else{
                                val = String.valueOf(val.toString());
                            }
                        }else if (valType == Integer.class){
                            val = Double.valueOf(val.toString()).intValue();
                        }else if (valType == Long.class){
                            val = Double.valueOf(val.toString()).longValue();
                        }else if (valType == Double.class){
                            val = Double.valueOf(val.toString());
                        }else if (valType == Float.class){
                            val = Float.valueOf(val.toString());
                        }else if (valType == Date.class){
                            val = DateUtil.getJavaDate((Double)val);
                        }else{
                            if (ef.fieldType() != Class.class){
                                val = ef.fieldType().getMethod("getValue", String.class).invoke(null, val.toString());
                            }else{
                                val = Class.forName(this.getClass().getName().replaceAll(this.getClass().getSimpleName(), 
                                        "fieldtype."+valType.getSimpleName()+"Type")).getMethod("getValue", String.class).invoke(null, val.toString());
                            }
                        }
                    } catch (Exception ex) {
                        log.info("Get cell value ["+i+","+column+"] error: " + ex.toString());
                        val = null;
                    }
                    // set entity value
                    if (os[1] instanceof Field){
                        Reflections.invokeSetter(e, ((Field)os[1]).getName(), val);
                    }else if (os[1] instanceof Method){
                        String mthodName = ((Method)os[1]).getName();
                        if ("get".equals(mthodName.substring(0, 3))){
                            mthodName = "set"+StringUtils.substringAfter(mthodName, "get");
                        }
                        Reflections.invokeMethod(e, mthodName, new Class[] {valType}, new Object[] {val});
                    }
                }
                sb.append(val+", ");
            }
            dataList.add(e);
            log.debug("Read success: ["+i+"] "+sb.toString());
        }
        return dataList;

    }

```

### 1.4.实体类###

```java
public class UserPojo {
    private String userName;   //用户名
    private String userPassword; //用户密码
    private String address;   //地址
     private String sex;      //性别
    private String UserNo;   //用户编号
    @ExcelField(title = "用户名" ,align = 2,sort=1)
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    @ExcelField(title = "用户密码" ,align = 2,sort=2)
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    @ExcelField(title = "地址" ,align = 2,sort=3)
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @ExcelField(title = "性别" ,align = 2,sort=4)
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    @ExcelField(title = "用户编号" ,align = 2,sort=5)
    public String getUserNo() {
        return UserNo;
    }
    public void setUserNo(String userNo) {
        UserNo = userNo;
    } 

}

```

###1.5.模板类###

```java
@RequestMapping(value = "import/template")
public String importFileTemplate(UserPojo user, 
                                 HttpServletResponse response, 
                                 HttpServletRequest request,
                                 RedirectAttributes redirectAttributes) {
        try {
            String fileName = "人员台账导入模板.xlsx";
            List<UserPojo> list = Lists.newArrayList(); 
            list.add(UserUtils.getUser());
       		new ExportExcel("人员台账信息", Employee.class, 2)
              			    .setDataList(list)
              			    .write(response, fileName, request).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
        }
        return "redirect:" +adminPath+ "/employee/employee/employeeSkillList?repage";
     }

```

###1.6.导入模板的例子数据###

```java
public class UserUtils {

    private static UserPojo user = new UserPojo();

    public static UserPojogetUser(){

       user.setUserName("小明");

       user.setUserPassword("123456");

       user.setAddress("安徽省");

       user.setSex("男");

       user.setUserNo("1706");

       return user;

    }

}

```

### 1.7.导入Excel

```java
	@RequiresPermissions("employee:employee:import")
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
        if (Global.isDemoMode()) {
            addMessage(redirectAttributes, "演示模式，不允许操作！");
            return "redirect:/user/userList" ;
        }
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(file, 1, 0);
            List<Employee> list = ei.getDataList(Employee.class);
            for (Employee employee : list) {
                try {
                    if(employee.getEmployeeNumber()!=null && employeeService.checkEmpNum(employee)
                            && employee.getEmployeeName()!=null && employee.getEmployeeMonth()!=null
                            && employee.getSkill()!=null && employee.getMainPost()!=null && employee.getStar()!=null
                            && employee.getPositionStatus()!=null
                            ){
                        BeanValidators.validateWithException(validator, employee);
                        employee.setManyStatus("1");
                        String status = employee.getPositionStatus();
                        status = "在岗".equals(status) ? "0" : "1";
                        employee.setPositionStatus(status);
                        employeeService.save(employee);
                        successNum++;
                    }else {
                        if(!employeeService.checkEmpNum(employee)){
                            failureMsg.append("<br/>工号 "+employee.getEmployeeNumber()+" 已存在; ");
                        }
                        if(employee.getEmployeeNumber()==null){
                            failureMsg.append("<br/>工号不能为空; ");
                        }
                        if(employee.getEmployeeName()==null){
                            failureMsg.append("<br/>姓名不能为空; ");
                        }
                        if(employee.getEmployeeMonth()==null){
                            failureMsg.append("<br/>月份不能为空; ");
                        }
                        if(employee.getSkill()==null){
                            failureMsg.append("<br/>技能列表不能为空; ");
                        }
                        if(employee.getMainPost()==null){
                            failureMsg.append("<br/>主技能不能为空; ");
                        }
                        if(employee.getPositionStatus()==null){
                            failureMsg.append("<br/>状态不能为空; ");
                        }
                        if(employee.getStar()==null){
                            failureMsg.append("<br/>星级不能为空; ");
                        }
                        failureNum++;
                    }

                } catch (ConstraintViolationException ex) {
                    failureMsg.append("<br/>员工工号 "+employee.getEmployeeNumber()+" 导入失败：");
                    List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
                    for (String message : messageList) {
                        failureMsg.append(message + "; ");
                        failureNum++;
                    }
                }catch (Exception ex) {
                    failureMsg.append("<br/>员工工号 "+employee.getEmployeeNumber()+" 导入失败："+ex.getMessage());
                }
            }
            if (failureNum > 0) {
                failureMsg.insert(0, "，失败 " + failureNum + " 条人员台账，导入信息如下：");
            }
            addMessage(redirectAttributes, "已成功导入 " + successNum + " 条人员台账" + failureMsg);
        } catch (Exception e) {
            addMessage(redirectAttributes, "人员台账导入失败！失败信息：" + e.getMessage());
        }
         return "redirect:/user/userList" ;

    }

```

### 1.8.导出Excel###

```java
    @RequiresPermissions("timer:timersInviteInfo:export")
    @RequestMapping(value = "Excel", method = RequestMethod.POST)
    public String Excel(TimersInviteInfo timersInviteInfo, HttpServletResponse response,
            RedirectAttributes redirectAttributes,HttpServletRequest request) {
        
      if (StringUtils.isNullOrEmpty(timersInviteInfo.getRegionId())) {
            User user = UserUtils.getUser();
            timersInviteInfo.setRegionId(user.getCompany().getId());
        }
        try {
            String fileName = "招标分析结果" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            List<TimersInviteInfo> list = timersInviteInfoService.findList(timersInviteInfo);
            new ExportExcel("招标分析结果", TimersInviteInfo.class).setDataList(list).write(response, fileName ,request).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出招标分析结果信息失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + Global.getAdminPath() + "/timer/timersInviteInfo/list?repage";
    }

```

### 1.9.前端jsp###

```jsp
<div id="importBox" class="hide">
        <form id="importForm" action="${ctx}/employee/employee/import" method="post" enctype="multipart/form-data"
            class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
            <input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
            <input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
            <a href="${ctx}/employee/employee/import/template">下载模板</a>
        </form>

    </div>

<form:form>

<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>

<li class="btns"><input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/></li>

</form:form>

<script type="text/javascript">

$(document).ready(function(){

$("#btnImport").click(function(){
                    $.jBox($("#importBox").html(), 
                    {title:"导入数据", buttons:{"关闭":true}, 
                    bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
                    });

               });

$("#btnExport").click(function(){
             top.$.jBox.confirm("确认要导出招标分析结果信息吗？","系统提示",function(v,h,f){
             if(v=="ok"){
                  $("#searchForm").attr("action","${ctx}/timer/timersInviteInfo/Excel");
                  $("#searchForm").submit();
                }
             },{buttonsFocus:1});
           top.$('.jbox-body .jbox-icon').css('top','55px');
    });

});

</script>

```




--------------------- 






