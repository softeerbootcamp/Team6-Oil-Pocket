import { addEvent, pipe } from "../common/function.js";
import { deleteOtherChatNode, getChatTextArray } from "./helperFunction.js";
import { chatBotAnswerView__myPage01, chatBotView } from "./view.js";

let chatBotArea = "";

const eventToChatBot = ($chatBotImg) => pipe(
    () => chatBotView(),
    ($chatBotContent) => {
        addEvent($chatBotImg, [
        () => $chatBotImg.after($chatBotContent),
        () => eventToChatBotText($chatBotContent)
        ]);

        return $chatBotContent.querySelector(".chatBotArea__closeBtn");
    },
    ($chatBotCloseBtn) => addEvent($chatBotCloseBtn, [() => $chatBotCloseBtn.closest(".chatBotArea").remove()])
)();

const eventToChatBotText = ($chatBotContent) => pipe(
    () => getChatTextArray($chatBotContent),
    ([$saveChat, $searchChat, $clientChat]) => {
        eventToSaveChat($saveChat);
        eventToSearchChat($searchChat);
        eventToClientChat($clientChat);
    }
)();

const eventToSaveChat = ($saveChat) => addEvent($saveChat, [
    () => chatBotArea = document.querySelector(".chatBotArea"),
    () => deleteOtherChatNode($saveChat),
    () => chatBotArea.appendChild(chatBotAnswerView__myPage01()),
]);

const eventToSearchChat = ($searchChat) => addEvent($searchChat, 
    [() => deleteOtherChatNode($searchChat)]);

const eventToClientChat = ($clientChat) => addEvent($clientChat, 
    [() => deleteOtherChatNode($clientChat)]);

export { eventToChatBot }