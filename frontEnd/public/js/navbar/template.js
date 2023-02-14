const getNavBarTemplate = () => `
    <div class="navbar__logo"><a href="/">Oil Pocket</a></div>
    <div class="navbar__btns">
        <strong><a href="/userDetail">jaewon님 안녕하세요</a></strong>
        <span> | </span>
        <strong class="navbar__myPageBtn"><a href="/login" data-link>로그인</a></strong>
        <span> | </span>
        <strong>다크모드</strong>
    </div>
`;

export { getNavBarTemplate }