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

    let myPrice = 310_000;
    let commonPrice = 330_000;

    makeComparisonCards($comparionContent, myPrice, commonPrice);

    return $comparionContainer;
}

export { comparisonView }