import { navBarView } from "../../navbar/view.js";
import { getComparisonTemplate } from "./template.js";

const comparisonView = () => {
    const $comparionContainer = document.createElement("section");
    const $comparionContent = document.createElement("section");
    $comparionContent.classList.add("main");

    $comparionContent.innerHTML = getComparisonTemplate();

    $comparionContainer.appendChild(navBarView());
    $comparionContainer.appendChild($comparionContent);

    return $comparionContainer;
}

export { comparisonView }