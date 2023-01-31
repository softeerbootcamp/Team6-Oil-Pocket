const getChatBotAreaTemplate = () => `
    <div class="chatBotArea__background">
        <div class="chatBotArea__closeArea">
            <img class="chatBotArea__closeBtn" src="./img/closeBtn.png" alt="chatBotCloseBtn">
        </div>
        <div class="chatBotArea__profile">
            <img src="./img/managerProfile.png" alt="profile">
            <span>안녕하세요 주행자님. 어떤 것을 도와드릴까요?</span>
        </div>
        <div class="chatBotArea__chat">
            <span>근처 주유소 검색은 어떻게 사용하나요?</span>
        </div>
        <div class="chatBotArea__chat">
            <span>출발지/도착지 주유소 검색은 어떻게 사용하나요?</span>
        </div>
        <div class="chatBotArea__chat">
            <span>다국어 제안을 하고 싶어요.</span>
        </div>
        <div class="chatBotArea__chat">
            <span>그 밖에 문의를 하고 싶어요.</span>
        </div>
    </div>
`;

export { getChatBotAreaTemplate }