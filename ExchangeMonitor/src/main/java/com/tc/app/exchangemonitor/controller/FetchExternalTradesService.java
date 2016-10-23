package com.tc.app.exchangemonitor.controller;

import org.hibernate.SQLQuery;

import com.tc.app.exchangemonitor.entitybase.IExternalTradeEntity;

import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class FetchExternalTradesService extends Service<ObservableList<IExternalTradeEntity>>
{
	private final SQLQuery sqlQuery;
	
	public FetchExternalTradesService()
	{
		sqlQuery = null;
	}
	
	public FetchExternalTradesService(SQLQuery sqlQuery)
	{
		this.sqlQuery = sqlQuery;
	}
	
	@Override
	protected Task<ObservableList<IExternalTradeEntity>> createTask()
	{
		FetchExternalTradesTask fetchExternalTradesTask = new FetchExternalTradesTask(sqlQuery);
		return fetchExternalTradesTask;
	}
	
	@Override
	protected void failed()
	{
		super.failed();
		System.out.println("Inside Failed.");
	}
	
	@Override
	protected void cancelled()
	{
		super.cancelled();
		System.out.println("Inside Cancelled.");
	}
	
	@Override
	protected void succeeded()
	{
		super.succeeded();
		System.out.println("Inside Succeeded.");
	}
	
	@Override
	protected void scheduled()
	{
		super.scheduled();
		System.out.println("Inside Scheduled.");
	}
	
	@Override
	protected void ready()
	{
		super.ready();
		System.out.println("Inside Ready.");
	}
	
	@Override
	protected void running()
	{
		super.running();
		System.out.println("Inside Running.");
	}
}