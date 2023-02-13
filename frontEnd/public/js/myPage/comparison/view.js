import { navBarView } from "../../navbar/view.js";
import { makeComparisonCards } from "./helperFunction.js";
import { getComparisonTemplate } from "./template.js";

const comparisonView = () => {
    const $comparionContainer = document.createElement("section");
    const $comparionContent = document.createElement("section");
    $comparionContent.classList.add("main");

    $comparionContent.innerHTML = getComparisonTemplate();

    $comparionContainer.appendChild(navBarView());
    $comparionContainer.appendChild($comparionContent);

    let myPrice = 290_000;
    let commonPrice = 280_000;
    let percent = 50;
    let age = "40대";
    let gender = "남자";

    makeComparisonCards($comparionContent, myPrice, commonPrice, percent, age, gender);

    return $comparionContainer;
}

export { comparisonView }