package com.sluzbenik.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/vakcina")
@Controller
// службеник може да види за сваки тип вакцине колика је доступна количина;
// сваки пут када се нека вакцина да грађанину, смањи се доступна количина те
// вакцине у систему;
// када дође нова количина вакцина од произвођача, службеник ажурира
// количину вакцина.
public class VakcinaController {

}
