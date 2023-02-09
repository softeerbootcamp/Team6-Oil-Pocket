import { changeCSS, pipe } from "../common/function";
import { getChatBotTemplate } from "./template";

NodeList.prototype.forEach = Array.prototype.forEach;

const getChatTextArray = ($chatBotContent) => $chatBotContent.querySelectorAll(".chatBotArea__chat");

const deleteOtherChatNode = ($exceptChatText) => pipe(
    ($chatTextArray) => $chatTextArray.forEach(($chatText, index) => {
        setTimeout(() => {
            if($chatText !== $exceptChatText) $chatText.remove();
            else changeCSS($exceptChatText, "color", "#000");
        }, 200 * index)
    })
)(document.querySelectorAll(".chatBotArea__chat"));

const initializeChatBotContent = ($chatBotContent) => {
    $chatBotContent.remove();
}

export { getChatTextArray, deleteOtherChatNode, initializeChatBotContent }