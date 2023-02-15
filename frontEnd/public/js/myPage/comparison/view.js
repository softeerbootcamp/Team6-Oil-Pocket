import { navBarView } from "../../navbar/view.js";
import { fetchComparisonCardData } from "./fetch.js";
import { makeComparisonCards } from "./helperFunction.js";
import { getComparisonTemplate } from "./template.js";

const comparisonView = async () => {
    const $comparionContainer = document.createElement("section");
    const $comparionContent = document.createElement("section");
    $comparionContent.classList.add("main");

    $comparionContent.innerHTML = getComparisonTemplate();

    $comparionContainer.appendChild(navBarView());
    $comparionContainer.appendChild($comparionContent);

    await fetchComparisonCardData($comparionContent);

    return $comparionContainer;
}

export { comparisonView }