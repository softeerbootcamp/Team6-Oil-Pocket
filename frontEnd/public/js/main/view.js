import { _$ } from "../common/function.js";
import { navBarView } from "../navbar/view.js";
import { eventToChatBot, eventToSaveChat, eventToSearchChat } from "./event.js";
import { 
    getChatBotTemplate, getMainViewContentTemplate, getManagerChatTemplate, 
    getMyPageAnswerChatTemplate01, getMyPageAnswerChatTemplate02,
    getGasAnswerChatTemplate01, getGasAnswerChatTemplate02, getGasAnswerChatTemplate03,
    getETCAnswerChatTemplate01, getETCAnswerChatTemplate02
} from "./template.js";

const mainView = async () => {
    const $mainViewContainer = document.createElement("section");
    const $mainViewContent = document.createElement("section");
    $mainViewContent.innerHTML = getMainViewContentTemplate();

    const $chatBotImg = _$(".chatBotArea__img", $mainViewContent);
    
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

const chatBotManagerView = () => {
    const $chatBotManagerView = document.createElement("div");
    $chatBotManagerView.classList.add("chatBotArea__managerProfile");
    $chatBotManagerView.innerHTML = getManagerChatTemplate();

    return $chatBotManagerView;
}

const chatBotQuestionView01 = () => {
    const $chatQuestioin01 = document.createElement("div");
    $chatQuestioin01.classList.add("chatBotArea__chat", "reChat");
    $chatQuestioin01.innerHTML = `<span>마이 페이지는 어떻게 활용하나요?</span>`;

    eventToSaveChat($chatQuestioin01);

    return $chatQuestioin01;
}

const chatBotQuestionView02 = () => {
    const $chatQuestioin02 = document.createElement("div");
    $chatQuestioin02.classList.add("chatBotArea__chat", "reChat");
    $chatQuestioin02.innerHTML = `<span>주변 주유소 검색은 어떻게 활용하나요?</span>`;

    eventToSearchChat($chatQuestioin02);

    return $chatQuestioin02;
}

const chatBotQuestionView03 = () => {
    const $chatQuestioin03 = document.createElement("div");
    $chatQuestioin03.classList.add("chatBotArea__chat", "reChat");
    $chatQuestioin03.innerHTML = `<span>그 밖에 문의를 하고 싶어요.</span>`;

    eventToSearchChat($chatQuestioin03);

    return $chatQuestioin03;
}

const chatBotAnswerView__myPage01 = () => {
    const $answerMyPage01 = document.createElement("div");
    $answerMyPage01.classList.add("chatBotArea__answer");
    $answerMyPage01.innerHTML = getMyPageAnswerChatTemplate01();

    return $answerMyPage01;
}

const chatBotAnswerView__myPage02 = () => {
    const $answerMyPage02 = document.createElement("div");
    $answerMyPage02.classList.add("chatBotArea__answer");
    $answerMyPage02.innerHTML = getMyPageAnswerChatTemplate02();

    return $answerMyPage02;
}

const chatBotAnswerView__gas01 = () => {
    const $answerGas01 = document.createElement("div");
    $answerGas01.classList.add("chatBotArea__answer");
    $answerGas01.innerHTML = getGasAnswerChatTemplate01();

    return $answerGas01;
}

const chatBotAnswerView__gas02 = () => {
    const $answerGas02 = document.createElement("div");
    $answerGas02.classList.add("chatBotArea__answer");
    $answerGas02.innerHTML = getGasAnswerChatTemplate02();

    return $answerGas02;
}

const chatBotAnswerView__gas03 = () => {
    const $answerGas03 = document.createElement("div");
    $answerGas03.classList.add("chatBotArea__answer");
    $answerGas03.innerHTML = getGasAnswerChatTemplate03();

    return $answerGas03;
}

const chatBotAnswerView__ETC01 = () => {
    const $answerETC01 = document.createElement("div");
    $answerETC01.classList.add("chatBotArea__answer");
    $answerETC01.innerHTML = getETCAnswerChatTemplate01();

    return $answerETC01;
}

const chatBotAnswerView__ETC02 = () => {
    const $answerETC02 = document.createElement("div");
    $answerETC02.classList.add("chatBotArea__answer");
    $answerETC02.innerHTML = getETCAnswerChatTemplate02();

    return $answerETC02;
}

export { 
    mainView, chatBotView, 
    chatBotAnswerView__myPage01, chatBotAnswerView__myPage02,
    chatBotManagerView, 
    chatBotQuestionView01, chatBotQuestionView02, chatBotQuestionView03,
    chatBotAnswerView__gas01, chatBotAnswerView__gas02, chatBotAnswerView__gas03,
    chatBotAnswerView__ETC01, chatBotAnswerView__ETC02
}