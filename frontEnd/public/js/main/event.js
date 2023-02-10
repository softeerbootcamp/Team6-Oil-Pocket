import { addEvent, changeCSS, makeNodeArrayLighterSubsequently, pipe } from "../common/function.js";
import { deleteChatNode, getChatTextArray } from "./helperFunction.js";
import { 
    chatBotAnswerView__myPage01, chatBotAnswerView__myPage02, 
    chatBotManagerView, chatBotView,
    chatBotAnswerView__gas01, chatBotAnswerView__gas02, chatBotAnswerView__gas03, 
    chatBotAnswerView__ETC01, chatBotAnswerView__ETC02,
    chatBotQuestionView01, chatBotQuestionView02, chatBotQuestionView03,
} from "./view.js";
NodeList.prototype.forEach = Array.prototype.forEach;

let chatBotArea = "";

const eventToChatBotCloseBtn = ($chatBotCloseBtn) => {
    const $chatBotArea = $chatBotCloseBtn.closest(".chatBotArea");
    const $explainAreaArray = document.querySelectorAll(".explainArea");
    addEvent($chatBotCloseBtn, [
        () => changeCSS($chatBotArea, "height", "5vh"),
        () => setTimeout(() => changeCSS($chatBotArea, "opacity", 0), 400),
        () =>  setTimeout(() => $chatBotArea.remove(), 800),
        () => $explainAreaArray.forEach(($explainArea) => {
            $explainArea.style.animationPlayState = "running";
        })
    ])
}

const eventToChatBot = ($chatBotImg) => {
    $chatBotImg.addEventListener("click", () => {
        const $chatBotView = chatBotView();
        const $chatBotBackGround = $chatBotView.querySelector(".chatBotArea__background");
        const $explainAreaArray = document.querySelectorAll(".explainArea");
        $explainAreaArray.forEach(($explainArea) => {
            $explainArea.style.animationPlayState = "paused";
        });

        $chatBotImg.after($chatBotView);
        setTimeout(() => {
            changeCSS($chatBotView, "height", "80vh");
            changeCSS($chatBotBackGround, "opacity", 1);
        }, 200);

        eventToChatBotText($chatBotView);

        const $chatBotCloseBtn = $chatBotView.querySelector(".chatBotArea__closeBtn");
        eventToChatBotCloseBtn($chatBotCloseBtn);
    })
}

const eventToChatBotText = ($chatBotContent) => pipe(
    () => getChatTextArray($chatBotContent),
    ([$saveChat, $searchChat, $clientChat]) => {
        eventToSaveChat($saveChat);
        eventToSearchChat($searchChat);
        eventToClientChat($clientChat);
    }
)();

const eventToSaveChat = ($saveChat) => addEvent($saveChat, [
    () => deleteChatNode(),
    () => chatBotArea = document.querySelector(".chatBotArea__background"),
    () => {
        const $answerText01 = chatBotAnswerView__myPage01();
        const $answerText02 = chatBotAnswerView__myPage02();
        const $chatBotManagerView = chatBotManagerView();
        const $questionText01 = chatBotQuestionView01();
        const $questionText02 = chatBotQuestionView02();
        const $questionText03 = chatBotQuestionView03();
        
        chatBotArea.appendChild($answerText01);
        chatBotArea.appendChild($answerText02);
        chatBotArea.appendChild($chatBotManagerView);
        chatBotArea.appendChild($questionText01);
        chatBotArea.appendChild($questionText02);
        chatBotArea.appendChild($questionText03);

        chatBotArea.scrollTop = chatBotArea.scrollHeight;

        makeNodeArrayLighterSubsequently(
            [$answerText01, $answerText02, $chatBotManagerView, $questionText01, $questionText02, $questionText03], 
        200);
    }
]);

const eventToSearchChat = ($searchChat) => addEvent($searchChat, [
    () => deleteChatNode(),
    () => chatBotArea = document.querySelector(".chatBotArea__background"),
    () => {
        const $answerText01 = chatBotAnswerView__gas01();
        const $answerText02 = chatBotAnswerView__gas02();
        const $answerText03 = chatBotAnswerView__gas03();
        const $chatBotManagerView = chatBotManagerView();
        const $questionText01 = chatBotQuestionView01();
        const $questionText02 = chatBotQuestionView02();
        const $questionText03 = chatBotQuestionView03();

        chatBotArea.appendChild($answerText01);
        chatBotArea.appendChild($answerText02);
        chatBotArea.appendChild($answerText03);
        chatBotArea.appendChild($chatBotManagerView);
        chatBotArea.appendChild($questionText01);
        chatBotArea.appendChild($questionText02);
        chatBotArea.appendChild($questionText03);

        chatBotArea.scrollTop = chatBotArea.scrollHeight;

        makeNodeArrayLighterSubsequently(
            [$answerText01, $answerText02, $answerText03, $chatBotManagerView, $questionText01, $questionText02, $questionText03], 
        200);
    }
]);

const eventToClientChat = ($clientChat) => addEvent($clientChat, [
    () => deleteChatNode(),
    () => chatBotArea = document.querySelector(".chatBotArea__background"),
    () => {
        const $answerText01 = chatBotAnswerView__ETC01();
        const $answerText02 = chatBotAnswerView__ETC02();
        const $chatBotManagerView = chatBotManagerView();
        const $questionText01 = chatBotQuestionView01();
        const $questionText02 = chatBotQuestionView02();
        const $questionText03 = chatBotQuestionView03();

        chatBotArea.appendChild($answerText01);
        chatBotArea.appendChild($answerText02);
        chatBotArea.appendChild($chatBotManagerView);
        chatBotArea.appendChild($questionText01);
        chatBotArea.appendChild($questionText02);
        chatBotArea.appendChild($questionText03);

        chatBotArea.scrollTop = chatBotArea.scrollHeight;

        makeNodeArrayLighterSubsequently(
            [$answerText01, $answerText02, $chatBotManagerView, $questionText01, $questionText02, $questionText03], 
        200);
    }
]);

export { eventToChatBot, eventToSaveChat, eventToSearchChat }