package com.nttdata.emea.devschool.vehicleordering.web.xml_roots;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.nttdata.emea.devschool.vehicleordering.business.Model;

@XmlRootElement
public class Models
{
	public List<Model> model;
}