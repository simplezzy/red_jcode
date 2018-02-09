问题：
 1.日期类是可变的，非线程安全
 2.定义混乱  Java.util.Date包含日期和时间 | java.sql.Date仅包含日期  无时区和国际化的支持
 3.SimpleDateFormat 对于时间戳格式化以及解析不明确

Java8   API是JSR-310的实现  工厂模式和策略模式
   java.time.LocalDate