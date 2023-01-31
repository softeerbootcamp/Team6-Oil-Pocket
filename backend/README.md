### Commit Convention
<hr>
ex) #{이슈번호} feat({클래스/패키지명/*}): 커밋메세지
<hr>

`add:` 새로운 코드를 추가할 경우

`update:` 코드를 고친 경우

`feat:` 새로운 기능을 추가할 경우

`fix:` 버그를 고친 경우

`design:` CSS 등 사용자 UI 디자인 변경

`breaking change:` 커다란 API 변경의 경우

`hotfix:` 급하게 치명적인 버그를 고쳐야하는 경우

`style:` 코드 포맷 변경, 세미 콜론 누락, 코드 수정이 없는 경우

`refactor:` 프로덕션 코드 리팩토링

`comment:` 필요한 주석 추가 및 변경

`docs:` 문서를 수정한 경우

`test:` 테스트 추가, 테스트 리팩토링(프로덕션 코드 변경 X)

`chore:` 빌드 태스트 업데이트, 패키지 매니저를 설정하는 경우(프로덕션 코드 변경 X)

`rename:` 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우

`remove:` 파일을 삭제하는 작업만 수행한 경우

<hr>

### Code Convention
1. 변수, 함수, 인스턴스명: 카멜케이스
2. 함수명: 동사+명사
3. Boolean 변수명: 조동사+flag 종류. ex) isNum, hasNum
4. 디자인패턴: 싱글톤
5. tab의 최대 depth는 4로 제한
6. 깃허브 커밋 컨벤션을 지킨다.
7. 함수에 대한 주석
