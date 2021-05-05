package com.haki.coronavirustracker.controller;

import com.haki.coronavirustracker.model.LocationStats;
import com.haki.coronavirustracker.service.CoronaVirusDataService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  private final CoronaVirusDataService coronaVirusDataService;

  public HomeController(
      CoronaVirusDataService coronaVirusDataService) {
    this.coronaVirusDataService = coronaVirusDataService;
  }

  @GetMapping("/")
  public String home(Model model) {
    List<LocationStats> allStats = coronaVirusDataService.getAllStats();
    int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
    int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
    model.addAttribute("locationStats", allStats);
    model.addAttribute("totalReportedCases", totalCases);
    model.addAttribute("totalNewCasesReported",totalNewCases);
    return "home";
  }
}
