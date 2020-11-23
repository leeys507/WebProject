package com.wp.controller.graph;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class GraphViewController {
	 @GetMapping("/yu/graph/graphView")    // view
	 public String openGraphView(Model model) {
		 return "graph/graph";
	 }
}
