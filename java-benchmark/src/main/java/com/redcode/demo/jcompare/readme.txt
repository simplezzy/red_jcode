List 排序方法总结：
  1. Collections.sort(userList, new Comparator<User>()
  2. Comparator.comparing(Address::getAddress);
  3. lambda 表达式
       userlist.sort((User h1, User h2) -> h1.getName().compareTo(h2.getName()));
      简写： userlist.sort((h1, h2) -> h1.getName().compareTo(h2.getName()));

jdk1.8 可能遇到的问题：
 1.usage of api documented as @since 1.6+
   A. File ->Project Structure->Project Settings -> Modules -> 你的Module名字 -> Sources -> Language Level->选个默认的就行
   B. 在maven build 里面添加如下的插件，设置Java的版本
      <build>
          <plugins>
              <plugin>
                  <groupId>org.apache.maven.plugins</groupId>
                  <artifactId>maven-compiler-plugin</artifactId>
                  <version>3.6.0</version>
                  <configuration>
                      <source>1.8</source>
                      <target>1.8</target>
                  </configuration>
              </plugin>
          </plugins>
      </build>