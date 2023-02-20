import { _$_ALL, changeCSS, pipe } from "../common/utils";

NodeList.prototype.forEach = Array.prototype.forEach;

const getChatTextArray = ($chatBotContent) => $chatBotContent.querySelectorAll(".chatBotArea__chat");

const deleteChatNode = ($exceptChatText) => pipe(
    ($chatTextArray) => $chatTextArray.forEach(($chatText, index) => {
        setTimeout(() => {
            if($chatText !== $exceptChatText) $chatText.remove();
            else changeCSS($exceptChatText, "color", "#000");
        }, 100 * index)
    })
)(_$_ALL(".chatBotArea__chat"));

const initializeChatBotContent = ($chatBotContent) => $chatBotContent.remove();

export { getChatTextArray, deleteChatNode, initializeChatBotContent }