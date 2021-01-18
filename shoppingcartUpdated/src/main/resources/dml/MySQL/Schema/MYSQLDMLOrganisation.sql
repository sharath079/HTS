create table ConfigProperties
(
 configPropertiesId int(10) primary key auto_increment

	
 , propertyName varchar(100)
		
 , propertyValue varchar(50)
	

		
 , vwLastModifiedDate datetime
 , vwLastModifiedTime int(9)
 , vwLastAction varchar(20)
 , vwModifiedBy varchar(100)
 , vwTxnRemarks varchar(500)
 , vwTxnStatus varchar(25)
 , isRequestUnderProcesss int(10)
 , legacyRecordId int(10)
 , vwCreationDate datetime
 , vwCreatedBy int(9)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	create table TaskInfo
(
 taskInfoId int(10) primary key auto_increment

	
 , taskName varchar(500)
		
 , taskDescription varchar(500)
		
 , taskType varchar(20)
		
 , apiName varchar(500)
		
 , targetEntityQuery varchar(100)
		
 , targetUserIdColumnAlias varchar(100)
		
 , targetEntityIdColumnAlias varchar(100)
		
 , enableInAppNotification varchar(20)
		
 , inAppNotificationLayout varchar(100)
		
 , enableEmailNotification varchar(20)
		
 , emailColumnAlias varchar(100)
		
 , emailNotificationLayout varchar(100)
		
 , emailSubject varchar(500)
		
 , enableSmsNotification varchar(20)
		
 , smsColumnAlias varchar(100)
		
 , smsNotificationLayout varchar(100)
		
 , sMSText varchar(500)
		
 , isActive varchar(20)
		
 , taskStartDate datetime
		
 , taskFrequency int(10)
		
 , taskFrequencyUnit varchar(20)
		
 , isRecurring varchar(20)
		
 , firstRunType varchar(20)
		
 , dateColumnAlias varchar(100)
		
 , firstRunDateCalculationMethod varchar(20)
		
 , firstRunDateGapInYears int(10)
		
 , firstRunDateGapInMonths int(10)
		
 , firstRunDateGapInDays int(10)
		
 , firstRunDateGapInHours int(10)
		
 , firstRunDateGapInMinutes int(10)
		
 , firstRunDateGapInSeconds int(10)
	

		
 , vwLastModifiedDate datetime
 , vwLastModifiedTime int(9)
 , vwLastAction varchar(20)
 , vwModifiedBy varchar(100)
 , vwTxnRemarks varchar(500)
 , vwTxnStatus varchar(25)
 , isRequestUnderProcesss int(10)
 , legacyRecordId int(10)
 , vwCreationDate datetime
 , vwCreatedBy int(9)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	create table TaskExecutionInfo
(
 taskExecutionInfoId int(10) primary key auto_increment

	
 , taskTimeCalculationType varchar(20)
		
 , firstRunDateCalculationMethod varchar(20)
		
 , firstRunDateGapInYears int(10)
		
 , firstRunDateGapInMonths int(10)
		
 , firstRunDateGapInDays int(10)
		
 , firstRunDateGapInHours int(10)
		
 , firstRunDateGapInMinutes int(10)
		
 , firstRunDateGapInSeconds int(10)
	

 , taskInfoId int(10)	

		
 , vwLastModifiedDate datetime
 , vwLastModifiedTime int(9)
 , vwLastAction varchar(20)
 , vwModifiedBy varchar(100)
 , vwTxnRemarks varchar(500)
 , vwTxnStatus varchar(25)
 , isRequestUnderProcesss int(10)
 , legacyRecordId int(10)
 , vwCreationDate datetime
 , vwCreatedBy int(9)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	create table TaskLayoutParameters
(
 taskLayoutParametersId int(10) primary key auto_increment

	
 , parameterName varchar(500)
		
 , parameterValueType varchar(40)
		
 , parameterValue varchar(40)
	

 , taskInfoId int(10)	

		
 , vwLastModifiedDate datetime
 , vwLastModifiedTime int(9)
 , vwLastAction varchar(20)
 , vwModifiedBy varchar(100)
 , vwTxnRemarks varchar(500)
 , vwTxnStatus varchar(25)
 , isRequestUnderProcesss int(10)
 , legacyRecordId int(10)
 , vwCreationDate datetime
 , vwCreatedBy int(9)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	create table EmailAttachmentLayout
(
 emailAttachmentLayoutId int(10) primary key auto_increment

	
 , emailAttachmentLayoutName varchar(500)
		
 , comments varchar(500)
	

 , taskInfoId int(10)	

		
 , vwLastModifiedDate datetime
 , vwLastModifiedTime int(9)
 , vwLastAction varchar(20)
 , vwModifiedBy varchar(100)
 , vwTxnRemarks varchar(500)
 , vwTxnStatus varchar(25)
 , isRequestUnderProcesss int(10)
 , legacyRecordId int(10)
 , vwCreationDate datetime
 , vwCreatedBy int(9)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	create table TaskScheduleInfo
(
 taskScheduleInfoId int(10) primary key auto_increment
 , taskInfoId int(10)

	
 , targetEntityId int(10)
		
 , targetUserId int(10)
		
 , notificationMedium varchar(30)
		
 , notificationLastSentTime datetime
		
 , nextNotificationTime datetime
	

		
 , vwLastModifiedDate datetime
 , vwLastModifiedTime int(9)
 , vwLastAction varchar(20)
 , vwModifiedBy varchar(100)
 , vwTxnRemarks varchar(500)
 , vwTxnStatus varchar(25)
 , isRequestUnderProcesss int(10)
 , legacyRecordId int(10)
 , vwCreationDate datetime
 , vwCreatedBy int(9)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	create table TaskSentInfo
(
 taskSentInfoId int(10) primary key auto_increment
 , taskInfoId int(10)

	
 , targetEntityId int(10)
		
 , targetUserId int(10)
		
 , notificationMedium varchar(30)
		
 , layoutInfoText varchar(1000)
		
 , notificationSentTime datetime
	

		
 , vwLastModifiedDate datetime
 , vwLastModifiedTime int(9)
 , vwLastAction varchar(20)
 , vwModifiedBy varchar(100)
 , vwTxnRemarks varchar(500)
 , vwTxnStatus varchar(25)
 , isRequestUnderProcesss int(10)
 , legacyRecordId int(10)
 , vwCreationDate datetime
 , vwCreatedBy int(9)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	create table UserCart
(
 userCartId int(10) primary key auto_increment

	
 , cartCreationTime time
		
 , cartCheckoutTime time
	

		
 , vwLastModifiedDate datetime
 , vwLastModifiedTime int(9)
 , vwLastAction varchar(20)
 , vwModifiedBy varchar(100)
 , vwTxnRemarks varchar(500)
 , vwTxnStatus varchar(25)
 , isRequestUnderProcesss int(10)
 , legacyRecordId int(10)
 , vwCreationDate datetime
 , vwCreatedBy int(9)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	create table CartItem
(
 cartItemId int(10) primary key auto_increment

	
 , userCartId int(20)
		
 , productId int(20)
		
 , productQuantity int(15)
		
 , productUnitPrice int(30)
		
 , subTotalAmount int(25)
		
 , grandTotal int(25)
	

		
 , vwLastModifiedDate datetime
 , vwLastModifiedTime int(9)
 , vwLastAction varchar(20)
 , vwModifiedBy varchar(100)
 , vwTxnRemarks varchar(500)
 , vwTxnStatus varchar(25)
 , isRequestUnderProcesss int(10)
 , legacyRecordId int(10)
 , vwCreationDate datetime
 , vwCreatedBy int(9)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	create table Products
(
 productsId int(10) primary key auto_increment

	
 , productName varchar(50)
		
 , productDescription varchar(50)
		
 , productPrice int(20)
		
 , productUnitType varchar(50)
	

		
 , vwLastModifiedDate datetime
 , vwLastModifiedTime int(9)
 , vwLastAction varchar(20)
 , vwModifiedBy varchar(100)
 , vwTxnRemarks varchar(500)
 , vwTxnStatus varchar(25)
 , isRequestUnderProcesss int(10)
 , legacyRecordId int(10)
 , vwCreationDate datetime
 , vwCreatedBy int(9)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	create table Orders
(
 ordersId int(10) primary key auto_increment

	
 , userCartId int(20)
		
 , orderNo int(15)
		
 , orderDate date
		
 , orderAmount int(50)
	

		
 , vwLastModifiedDate datetime
 , vwLastModifiedTime int(9)
 , vwLastAction varchar(20)
 , vwModifiedBy varchar(100)
 , vwTxnRemarks varchar(500)
 , vwTxnStatus varchar(25)
 , isRequestUnderProcesss int(10)
 , legacyRecordId int(10)
 , vwCreationDate datetime
 , vwCreatedBy int(9)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	create table OrderItem
(
 orderItemId int(10) primary key auto_increment

	
 , orderId int(15)
		
 , productId int(20)
		
 , productQuantity int(15)
		
 , productUnitPrice int(15)
		
 , subTotalAmt int(20)
	

		
 , vwLastModifiedDate datetime
 , vwLastModifiedTime int(9)
 , vwLastAction varchar(20)
 , vwModifiedBy varchar(100)
 , vwTxnRemarks varchar(500)
 , vwTxnStatus varchar(25)
 , isRequestUnderProcesss int(10)
 , legacyRecordId int(10)
 , vwCreationDate datetime
 , vwCreatedBy int(9)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
