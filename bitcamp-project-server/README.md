# eomcs-java-project-6.2-server

Log4j를 사용하여 애플리케이션 로그 처리하기

- Log4j를 설정하고 이용하는 방법
- Log4j2를 설정하고 이용하는 방법

## 프로젝트 - 수업관리 시스템  

애플리케이션의 실행 상태를 확인하고 싶을 때 보통 `System.out.println()`을 사용하여 변수의 값이나 
메서드의 리턴 값, 객체의 필드 값을 출력한다. 
이 방식의 문제는 개발을 완료한 후 이런 코드를 찾아 제거하기가 매우 번거롭다는 것이다. 
또한 콘솔 출력이 아닌 파일이나 네트웍으로 출력하려면 별개의 코드를 작성해야 한다.
이런 문제점을 해결하기 위해 나온 것이 `Log4j`라는 라이브러리이다.
개발 중에는 로그를 자세하게 출력하고 개발이 완료된 후에는 중요 로그만 출력하도록 조정하는 기능을 제공한다.
로그의 출력 형식을 지정할 수 있다. 출력 대상도 콘솔, 파일, 네트워크, DB 등 다양하게 지정할 수 있다.

### ver 6.2.0 - `System.out.println()` 대신에 Log4j를 적용하여 로그를 출력하라.

#### 1단계) Log4j 라이브러리를 추가한다.

- 라이브러리 정보 알아내기
    - `mvnrepository.com`에서 `log4j`를 검색한다.
- build.gradle
    - `log4j` 라이브러리 정보를 추가한다.
    - `$ gradle eclipse`를 실행하여 이클립스 설정 파일을 갱신한다.
    - 이클립스 워크스페이스에 로딩되어 있는 클래스를 갱신한다.

#### 2단계) Log4j 설정 파일을 추가한다.

- src/main/resources 
    - 애플리케이션이 실행 중에 사용하는 `.properties`, `.xml`, `.txt` 와 같은 설정 파일을 두는 디렉토리이다.
    - 디렉토리를 추가한 후, `$ gradle eclipse` 를 다시 실행하여 이클립스 설정 파일을 갱신한다.
    - 그래야만 `resources` 폴더가 소스 폴더가 된다.
- log4j.properties
    - `Log4j` 의 출력 대상, 출력 형식 등을 정의한 설정 파일이다.
    - 자바 CLASSPATH의 루트 디렉토리에 파일을 둔다.
- AppConfig.java
    - `SqlSessionFactory`를 생성할 때 MyBatis에서 사용할 로깅 엔진을 `LOG4J`로 지정한다.


#### 3단계) 각 클래스의 로그 출력을 Log4j로 전환한다.

- App.java
    - 기존에는 `System.out.println()`을 사용하여 출력하였다.
    - Log4j로 전환한다.
- ContextLoaderListener.java
    - 기존에는 `System.out.println()`을 사용하여 출력하였다.
    - Log4j로 전환한다.


##### 실습 결과

`eomcs-java-project-server` 프로젝트의 `App` 클래스를 실행한다.
```
DEBUG [main] - DataLoaderListener.contextInitialized() 실행!
DEBUG [main] - Logging initialized using 'class org.apache.ibatis.logging.log4j.Log4jImpl' adapter.
DEBUG [main] - Class not found: org.jboss.vfs.VFS
DEBUG [main] - JBoss 6 VFS API is not available in this environment.
DEBUG [main] - Class not found: org.jboss.vfs.VirtualFile
DEBUG [main] - VFS implementation org.apache.ibatis.io.JBoss6VFS is not valid in this environment.
DEBUG [main] - Using VFS adapter org.apache.ibatis.io.DefaultVFS
DEBUG [main] - Find JAR URL: file:/Users/eomjinyoung/git/eomcs-java-project/eomcs-java-project-6.2.0-server/bin/main/com/eomcs/lms/domain
DEBUG [main] - Not a JAR: file:/Users/eomjinyoung/git/eomcs-java-project/eomcs-java-project-6.2.0-server/bin/main/com/eomcs/lms/domain
DEBUG [main] - Reader entry: Board.class
DEBUG [main] - Reader entry: Lesson.class
DEBUG [main] - Reader entry: Member.class
DEBUG [main] - Reader entry: PhotoBoard.class
DEBUG [main] - Reader entry: PhotoFile.class
DEBUG [main] - Listing file:/Users/eomjinyoung/git/eomcs-java-project/eomcs-java-project-6.2.0-server/bin/main/com/eomcs/lms/domain
DEBUG [main] - Find JAR URL: file:/Users/eomjinyoung/git/eomcs-java-project/eomcs-java-project-6.2.0-server/bin/main/com/eomcs/lms/domain/Board.class
DEBUG [main] - Not a JAR: file:/Users/eomjinyoung/git/eomcs-java-project/eomcs-java-project-6.2.0-server/bin/main/com/eomcs/lms/domain/Board.class
DEBUG [main] - Reader entry: ����