 java 1.5开始引入了注解和反射，正确的来说注解是反射的一部分，
 没有反射，注解无法正常使用，但离开注解，反射依旧可以使用，因此来说，反射的定义应该包含注解才合理一些。
 注解的功能分为2部分：
    1、作为特定的标记
    2、额外信息的载体
@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserAnnotation {
    public int id() default 0;
    public String name() default "";
    public int age() default 18;
    public String gender() default "M";
}