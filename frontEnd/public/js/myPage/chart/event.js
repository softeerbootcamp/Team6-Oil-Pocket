import { _$, _$_ALL, addEvent, toggleArrayClass } from "../../common/function";

const eventToChartLegend = ($container) => {
    const $commonLegend = _$(".oilInfoArea__legend--common", $container);
    const $userLegend = _$(".oilInfoArea__legend--user", $container);
    const $commonCharts = _$_ALL(".oilInfoArea__averageChart", $container);
    const $userCharts = _$_ALL(".oilInfoArea__myChart", $container);

    addEvent($commonLegend, [() => toggleArrayClass($commonCharts, "hidden")]);
    addEvent($userLegend, [() => toggleArrayClass($userCharts, "hidden")]);
}

export { eventToChartLegend }