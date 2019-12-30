// 생성자 활용 예 - 자바에서 제공하는 클래스 사용을 통해 생성자 활용을 익혀보자!
package com.eomcs.oop.ex04;

import java.util.Properties;

public class Exam0113 {

  public static void main(String[] args) throws Exception {

    // 한글 문자 코드의 바이트 배열을 가지고 String 인스턴스 초기화시키기
    byte[] bytes = {
        (byte)0x41, // A
        (byte)0x42, // B
        (byte)0x43, // C
        (byte)0x61, // a
        (byte)0x62, // b
        (byte)0x63, // c
        (byte)0x30, // 0
        (byte)0x31, // 1
        (byte)0x32, // 2
        (byte)0x20, // space
        (byte)0x21, // !
        (byte)0x23, // #
        (byte)0x25, // %
        (byte)0x2b, // +
        (byte)0xea, (byte)0xb0, (byte)0x80, // 가
        (byte)0xea, (byte)0xb0, (byte)0x81, // 각
        (byte)0xeb, (byte)0x98, (byte)0x98, // 똘
        (byte)0xeb, (byte)0x98, (byte)0xa5};// 똠
    String s1 = new String(bytes);
    System.out.println(s1);
    
    // 생성자에 바이트 배열을 넘길 때
    // 바이트 배열에 들어있는 데이터의 문자 코드표를 알려주지 않으면,
    // String 생성자는 OS의 기본 문자 코드표로 간주하여 변환.
    //
    // 따라서 위 예제는 Linux, macOS, Unix의 CLI에서 정상 출력되지만,
    // Windows CLI환경에서는 깨진다.
    //
    // 이클립스에서는 기본 인코딩이 UTF-8이라 깨지지 않는다.
    //
    // Windows CLI에서 깨지지 않게 하는 방법
    // => cmd에서 java -Dfile.encoding=UTF-8 -cp bin/main com.eomcs.oop.ex04.Exam0113
    //    PowerShell에서는 -Dfile.encoding=UTF-8 옵션을 제대로 처리하지 못한다.
  }
}












