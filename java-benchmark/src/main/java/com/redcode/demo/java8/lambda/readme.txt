java8 引入新的操作符  ->  箭头操作符
左侧： Lambda 表达式的参数列表
右侧： Lambda 表达式中所需要执行的功能，Lambda体

左右遇一括号省
左侧推断类型省
能省则省

语法：
  1. 无参数，无返回值 Runnabal
     () -> System.out.println("Hello");

  2. 一个参数，无返回值 Consumer
     Consumer<String> con = x -> System.out.println(x);
     con.accept("abcg");

  3. 两个以上的参数，有返回值，并且Lambda体中有多条语句
     Comparator<Integer> com = (x, y) -> {
            System.out.println("hello");
            return Integer.compare(x,y);
          }

  4. 两个以上的参数，有返回值，并且Lambda体中有一条语句 return 和大括号都可以省略不写
      Comparator<Integer> com = (x, y) -> Integer.compare(x,y);

  5. Lambda表达式的参数类型可以省略，JVM编译器进行上下文的类型推断

  注意：Lambda需要函数式接口的支持：接口中还有一个抽象方法时  可以采用注解@FunctionalInterface 修饰 检查是否是函数式接口

