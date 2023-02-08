import { navBarView } from "../navbar/view.js";
import { eventToChatBot } from "./event.js";
import { getChatBotTemplate, getMainViewContentTemplate } from "./template.js";

const mainView = () => {
    const $mainViewContainer = document.createElement("section");
    const $mainViewContent = document.createElement("section");

    $mainViewContent.innerHTML = getMainViewContentTemplate();

    const $chatBotImg = $mainViewContent.querySelector(".chatBotArea__img");
    
    eventToChatBot($chatBotImg);

    $mainViewContainer.appendChild(navBarView());
    $mainViewContainer.appendChild($mainViewContent);

    return $mainViewContainer;
}

const chatBotView = () => {
    const $chatBotContainer = document.createElement("section");
    $chatBotContainer.classList.add("chatBotArea");
    $chatBotContainer.innerHTML = getChatBotTemplate();

    return $chatBotContainer;
}

const chatBotAnswerView__myPage01 = () => {
    const $answerMyPage01 = document.createElement("div");
    $answerMyPage01.classList.add("chatBotArea__answer");
    $answerMyPage01.innerHTML = `
        <span>마이 페이지는 프로필 수정, 주유 기록 입력, 이번 달 비교, 월별 비교, 주유 기록 열람 텝으로 이루어져 있어요.</span>
        <span>주행자님의 인적 정보를 자유롭게 수정하고, 주유 기록을 관리할 수 있는 공간이에요.</span>
    `;

    return $answerMyPage01;
}

export { mainView, chatBotView, chatBotAnswerView__myPage01 }