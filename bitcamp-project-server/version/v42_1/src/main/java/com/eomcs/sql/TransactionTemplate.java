package com.eomcs.sql;

public class TransactionTemplate {
  PlatformTransactionManager txManager;

  public TransactionTemplate(PlatformTransactionManager txManager) {
    this.txManager = txManager;
  }

  public Object execute(TransactionCallback action) throws Exception {
    // 이 메서드는 트랜잭션 작업을 어떻게 처리할 것인지
    // 실행 틀을 만드는 것이다.
    // 파라미터로 받은 작업을 트랜잭션으로 실행한다.
    txManager.beginTransaction();

    try {
      // 트랜잭션을 수행한 후 리턴할 결과를 담을 변수
      Object result = null;
      // 트랜잭션으로 묶인 작업
      result = action.doInTransaction();

      // 예외가 발생하지 않고 정상적으로 실행되었으면 수행한 작업을 적용한다.
      txManager.commit();

      // 트랜잭션 작업 결과를 리턴한다.
      return result;
    } catch (Exception e) {
      // 예외가 발생하면 지금까지 한 작업을 취소한다.
      txManager.rollback();
      // 발생한 예외는 이 메서드를 호출한 쪽에 그대로 전달한다.
      throw e;
    }
  }
}
