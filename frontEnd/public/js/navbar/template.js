const getNonMemberNavBarTemplate = () => `
    <div class="navbar__logo"><a href="/">Oil Pocket</a></div>
    <div class="navbar__btns">
        <strong class="navbar__myPageBtn"><a href="/login" data-link>로그인</a></strong>
    </div>
`;

const getMemberNavBarTemplate = (userID) => `
    <div class="navbar__logo"><a href="/">Oil Pocket</a></div>
    <div class="navbar__btns">
        <strong><a href="/userDetail">${userID}님 안녕하세요</a></strong>
        <span> | </span>
        <strong class="navbar__myPageBtn">로그아웃</a></strong>
    </div>
`;

export { getNonMemberNavBarTemplate, getMemberNavBarTemplate }