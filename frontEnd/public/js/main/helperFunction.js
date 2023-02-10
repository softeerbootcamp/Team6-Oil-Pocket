import { changeCSS, pipe } from "../common/function";
import { getChatBotTemplate } from "./template";

NodeList.prototype.forEach = Array.prototype.forEach;

const getChatTextArray = ($chatBotContent) => $chatBotContent.querySelectorAll(".chatBotArea__chat");

const deleteChatNode = ($exceptChatText) => pipe(
    ($chatTextArray) => $chatTextArray.forEach(($chatText, index) => {
        setTimeout(() => {
            if($chatText !== $exceptChatText) $chatText.remove();
            else changeCSS($exceptChatText, "color", "#000");
        }, 100 * index)
    })
)(document.querySelectorAll(".chatBotArea__chat"));

const initializeChatBotContent = ($chatBotContent) => {
    $chatBotContent.remove();
}

export { getChatTextArray, deleteChatNode, initializeChatBotContent }