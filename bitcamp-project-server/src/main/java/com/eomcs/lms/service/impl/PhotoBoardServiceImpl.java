package com.eomcs.lms.service.impl;

import java.util.List;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.service.PhotoBoardService;
import com.eomcs.sql.PlatformTransactionManager;
import com.eomcs.sql.TransactionTemplate;

public class PhotoBoardServiceImpl implements PhotoBoardService {
  // 트랜잭션 도우미 객체를 이용하여 트랜잭션 작업을 처리해보자.
  // => 트랜잭션으로 묶어서 처리할 작업은 TransactionCallback 규칙에 따라
  // 객체를 만들어 parameter로 넘겨주면 된다.
  // execute()안에 TransactionCallback 인터페이스의 doInTransaction() 구현
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;
  TransactionTemplate transactionTemplate;

  public PhotoBoardServiceImpl(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao,
      PlatformTransactionManager txManager) {

    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    // 트랜잭션 관리자를 사용하여 트랜잭션을 처리할 도우미 객체를 준비한다.
    this.transactionTemplate = new TransactionTemplate(txManager);
  }


  @Override
  public void add(PhotoBoard photoBoard) throws Exception {
    transactionTemplate.execute(() -> {
      if (photoBoardDao.insert(photoBoard) == 0) {
        throw new Exception("사진 게시물 등록에 실패했습니다.");
      }
      photoFileDao.insert(photoBoard);
      return null;
    });

  }


  @Override
  public List<PhotoBoard> listLessonPhoto(int lessonNo) throws Exception {
    return photoBoardDao.findAllByLessonNo(lessonNo);
  }


  @Override
  public PhotoBoard get(int no) throws Exception {
    return photoBoardDao.findByNo(no);
  }


  @Override
  public void update(PhotoBoard photoBoard) throws Exception {
    transactionTemplate.execute(() -> {
      if (photoBoardDao.update(photoBoard) == 0) {
        throw new Exception("사진 게시글 변경에 실패했습니다.");
      }

      if (photoBoard.getFiles() != null) {
        // 첨부파일을 변경할 시
        photoFileDao.deleteAll(photoBoard.getNo());
        photoFileDao.insert(photoBoard);
      }
      return null;
    });

  }


  @Override
  public void delete(int no) throws Exception {
    transactionTemplate.execute(() -> {
      photoFileDao.deleteAll(no);
      if (photoBoardDao.delete(no) == 0) {
        throw new Exception("삭제에 실패했습니다.");
      }
      return null;
    });

  }
}
