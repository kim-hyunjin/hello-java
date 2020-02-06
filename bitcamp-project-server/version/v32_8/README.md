# 32_7 - DAO 클래스의 공통점을 뽑아 수퍼 클래스로 정의하기

## 학습목표

- 상속 기법 중 generalization을 이해한다.
- generalization 구현

### 상속

- specialization
  - 수퍼 클래스를 상속 받아 특별한 기능을 수행하는 서브 클래스 만들기
- generalization
  - 클래스들의 공통점을 뽑아 수퍼 클래스로 만든 후에 상속 관계 맺기

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/dao/AbstractObjectFileDao.java 추가
- src/main/java/com/eomcs/lms/dao/BoardObjectFileDao.java 변경
- src/main/java/com/eomcs/lms/dao/LessonObjectFileDao.java 변경
- src/main/java/com/eomcs/lms/dao/MemberObjectFileDao.java 변경
- src/main/java/com/eomcs/lms/ServerApp.java 변경

## 실습

### 훈련 1: DAO의 공통점을 뽑아 수퍼 클래스로 정의하라

- com.eomcs.lms.dao/AbstractObjectFileDao.java를 생성한다.

### 훈련 2: BoardObjectFileDao가 위에서 정의한 클래스를 상속받도록 변경하라.

- com.eomcs.lms.dao/BoardObjectFileDao.java 변경

### 훈련 3: LessonObjectFileDao가 위에서 정의한 클래스를 상속받도록 변경하라.

- com.eomcs.lms.dao/LessonObjectFileDao.java 변경

### 훈련 4: MemberObjectFileDao가 위에서 정의한 클래스를 상속받도록 변경하라.

- com.eomcs.lms.dao/MemberObjectFileDao.java 변경
