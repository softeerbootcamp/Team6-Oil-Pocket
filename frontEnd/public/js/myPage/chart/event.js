import { addEvent, toggleArrayClass } from "../../common/function";

const eventToChartLegend = ($container) => {
    const $commonLegend = $container.querySelector(".oilInfoArea__legend--common");
    const $userLegend = $container.querySelector(".oilInfoArea__legend--user");
    const $commonCharts = $container.querySelectorAll(".oilInfoArea__averageChart");
    const $userCharts = $container.querySelectorAll(".oilInfoArea__myChart");

    addEvent($commonLegend, [() => toggleArrayClass($commonCharts, "hidden")]);
    addEvent($userLegend, [() => toggleArrayClass($userCharts, "hidden")]);
}

export { eventToChartLegend }