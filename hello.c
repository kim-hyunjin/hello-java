#include <stdio.h>

int main() {
  printf("Hello!\n I'm C");
  int a = 100;

  if (a > 0) {
    printf("양수입니다!\n");
  } else {
    prentf("음수입니다!\n"); //컴파일 과정중 문법오류 검사
  }
}