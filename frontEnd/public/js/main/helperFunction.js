import { _$_ALL, changeCSS } from "../common/utils";

NodeList.prototype.forEach = Array.prototype.forEach;

const getChatTextArray = ($chatBotContent) => $chatBotContent.querySelectorAll(".chatBotArea__chat");

const deleteChatNode = ($exceptChatText) => {
    const $chatTextArray = _$_ALL(".chatBotArea__chat");
    $chatTextArray.forEach(($chatText, index) => setTimeout(() => {
        if($chatText !== $exceptChatText) $chatText.remove();
        else changeCSS($exceptChatText, "color", "#000");
    }, 100 * index))
}

const initializeChatBotContent = ($chatBotContent) => $chatBotContent.remove();

export { getChatTextArray, deleteChatNode, initializeChatBotContent }