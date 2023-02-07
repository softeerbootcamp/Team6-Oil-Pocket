import { addEvent, pipe } from "../common/function.js";
import { deleteOtherChatNode, getChatTextArray } from "./helperFunction.js";
import { chatBotView } from "./view.js";

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
    ([$saveChat, $searchChat, $languageSuggestText, $clientChat]) => {
        eventToSaveChat($saveChat);
        eventToSearchChat($searchChat);
        eventToLanguageChat($languageSuggestText);
        eventToClientChat($clientChat);
    }
)();

const eventToSaveChat = ($saveChat) => addEvent($saveChat, [
    () => deleteOtherChatNode($saveChat)
]);

const eventToSearchChat = ($searchChat) => addEvent($searchChat, [
    () => deleteOtherChatNode($searchChat)
]);


const eventToLanguageChat = ($languageChat) => addEvent($languageChat, [
    () => deleteOtherChatNode($languageChat)
]);


const eventToClientChat = ($clientChat) => addEvent($clientChat, [
    () => deleteOtherChatNode($clientChat)
]);


export { eventToChatBot }