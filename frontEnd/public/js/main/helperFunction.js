import { pipe } from "../common/function";

NodeList.prototype.forEach = Array.prototype.forEach;

const getChatTextArray = ($chatBotContent) => $chatBotContent.querySelectorAll(".chatBotArea__chat");

const deleteOtherChatNode = ($exceptChatText) => pipe(
    ($chatTextArray) => $chatTextArray.forEach(($chatText) => {
        if($chatText !== $exceptChatText) 
            $chatText.remove();
    })
)(document.querySelectorAll(".chatBotArea__chat"));

export { getChatTextArray, deleteOtherChatNode }