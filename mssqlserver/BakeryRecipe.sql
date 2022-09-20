-- ************************************** [Member]
CREATE TABLE [Member]
(
 [member_id] bigint NOT NULL ,
 [name]      varchar(50) NOT NULL ,
 [email]     varchar(50) NOT NULL ,
 [DOB]       date NOT NULL ,
 [username]  varchar(50) NOT NULL ,
 [password]  varchar(50) NOT NULL ,
 [avartar]   varchar(50) NULL ,


 CONSTRAINT [Role_User_PK] PRIMARY KEY CLUSTERED ([member_id] ASC)
);
GO
-- ************************************** [Role]
CREATE TABLE [Role]
(
 [role_id]   int NOT NULL ,
 [role_name] varchar(50) NOT NULL ,


 CONSTRAINT [Role_PK] PRIMARY KEY CLUSTERED ([role_id] ASC)
);
GO
-- ************************************** [Member_Role]
CREATE TABLE [Member_Role]
(
 [member_id] bigint NOT NULL ,
 [role_id]   int NOT NULL ,


 CONSTRAINT [UR_PK] PRIMARY KEY CLUSTERED ([member_id] ASC, [role_id] ASC),
 CONSTRAINT [Member_UR_RS] FOREIGN KEY ([member_id])  REFERENCES [Member]([member_id]),
 CONSTRAINT [Role_UR_RS] FOREIGN KEY ([role_id])  REFERENCES [Role]([role_id])
);
GO

-- ************************************** [Friend]
CREATE TABLE [Friend]
(
 [member_id] bigint NOT NULL ,
 [frend_id]  bigint NOT NULL ,
 [status]    smallint NOT NULL ,


 CONSTRAINT [Friend_PK] PRIMARY KEY CLUSTERED ([member_id] ASC, [frend_id] ASC),
 CONSTRAINT [Member_Friend_RS] FOREIGN KEY ([member_id])  REFERENCES [Member]([member_id]),
 CONSTRAINT [Member_Friend_RS_2] FOREIGN KEY ([frend_id])  REFERENCES [Member]([member_id])
);
GO
-- ************************************** [Message]
CREATE TABLE [Message]
(
 [massage_id]         bigint NOT NULL ,
 [massage_body]       nvarchar(500) NOT NULL ,
 [member_sender_id]   bigint NOT NULL ,
 [member_receiver_id] bigint NOT NULL ,
 [mss_create_date]    datetime NOT NULL ,


 CONSTRAINT [Massage_PK] PRIMARY KEY CLUSTERED ([massage_id] ASC),
 CONSTRAINT [Member_Message_Receiver_RS] FOREIGN KEY ([member_receiver_id])  REFERENCES [Member]([member_id]),
 CONSTRAINT [Member_Message_Sender_RS] FOREIGN KEY ([member_sender_id])  REFERENCES [Member]([member_id])
);
GO
-- ************************************** [Post]
CREATE TABLE [Post]
(
 [post_id]     bigint NOT NULL ,
 [member_id]   bigint NOT NULL ,
 [image]       varchar(50) NULL ,
 [video]       varchar(50) NULL ,
 [create_date] datetime NOT NULL ,
 [tool]        nvarchar(500) NOT NULL ,
 [steps]       nvarchar(3000) NOT NULL ,


 CONSTRAINT [Post_PK] PRIMARY KEY CLUSTERED ([post_id] ASC),
 CONSTRAINT [User_Post_RS] FOREIGN KEY ([member_id])  REFERENCES [Member]([member_id])
);
GO
-- ************************************** [Post_Image]
CREATE TABLE [Post_Image]
(
 [post_image_id] bigint NOT NULL ,
 [post_id]       bigint NOT NULL ,
 [image]         varchar(50) NOT NULL ,


 CONSTRAINT [Post_Image_PK] PRIMARY KEY CLUSTERED ([post_image_id] ASC),
 CONSTRAINT [Post_PI_RS] FOREIGN KEY ([post_id])  REFERENCES [Post]([post_id])
);
GO
-- ************************************** [Post_Video]
CREATE TABLE [Post_Video]
(
 [post_video_id] bigint NOT NULL ,
 [post_id]       bigint NOT NULL ,
 [video]         varchar(50) NOT NULL ,


 CONSTRAINT [Post_Video_PK] PRIMARY KEY CLUSTERED ([post_video_id] ASC),
 CONSTRAINT [Post_PV_RS] FOREIGN KEY ([post_id])  REFERENCES [Post]([post_id])
);
GO
-- ************************************** [Comment]
CREATE TABLE [Comment]
(
 [comment_id]     bigint NOT NULL ,
 [member_id]      bigint NOT NULL ,
 [post_id]        bigint NOT NULL ,
 [comment_detail] nvarchar(500) NOT NULL ,
 [image]          varchar(50) NULL ,
 [video]          varchar(50) NULL ,


 CONSTRAINT [Commnet_PK] PRIMARY KEY CLUSTERED ([comment_id] ASC),
 CONSTRAINT [Post_Comment_RS] FOREIGN KEY ([post_id])  REFERENCES [Post]([post_id]),
 CONSTRAINT [Member_Comment_RS] FOREIGN KEY ([member_id])  REFERENCES [Member]([member_id])
);
GO
-- ************************************** [Food]
CREATE TABLE [Food]
(
 [post_id]     bigint NOT NULL ,
 [name]        nvarchar(50) NOT NULL ,
 [description] nvarchar(500) NOT NULL ,


 CONSTRAINT [Food_PK] PRIMARY KEY CLUSTERED ([post_id] ASC),
 CONSTRAINT [Post_Food_RS] FOREIGN KEY ([post_id])  REFERENCES [Post]([post_id])
);
GO
-- ************************************** [Material]
CREATE TABLE [Material]
(
 [material_id]   bigint NOT NULL ,
 [material_name] nvarchar(50) NOT NULL ,
 [description]   nvarchar(500) NOT NULL ,


 CONSTRAINT [Material_PK] PRIMARY KEY CLUSTERED ([material_id] ASC)
);
GO
-- ************************************** [Material_Detail]
CREATE TABLE [Material_Detail]
(
 [material_detail_id] bigint NOT NULL ,
 [material_id]        bigint NOT NULL ,
 [post_id]            bigint NOT NULL ,


 CONSTRAINT [Material_Detail_PK] PRIMARY KEY CLUSTERED ([material_detail_id] ASC),
 CONSTRAINT [Material_MD_RS] FOREIGN KEY ([material_id])  REFERENCES [Material]([material_id]),
 CONSTRAINT [Post_MD_RS] FOREIGN KEY ([post_id])  REFERENCES [Post]([post_id])
);
GO
-- ************************************** [Store]
CREATE TABLE [Store]
(
 [store_id]   bigint NOT NULL ,
 [store_name] varchar(50) NOT NULL ,
 [member_id]  bigint NOT NULL ,
 [address]    nvarchar(500) NOT NULL ,
 [phone]      varchar(50) NOT NULL ,


 CONSTRAINT [Store_PK] PRIMARY KEY CLUSTERED ([store_id] ASC),
 CONSTRAINT [Menber_Store_RS] FOREIGN KEY ([member_id])  REFERENCES [Member]([member_id])
);
GO
-- ************************************** [Store_Material]
CREATE TABLE [Store_Material]
(
 [material_id] bigint NOT NULL ,
 [store_id]    bigint NOT NULL ,
 [price]       int NOT NULL ,
 [quantity]    int NOT NULL ,


 CONSTRAINT [Store_Material_PK] PRIMARY KEY CLUSTERED ([material_id] ASC, [store_id] ASC),
 CONSTRAINT [Material_SM_RS] FOREIGN KEY ([material_id])  REFERENCES [Material]([material_id]),
 CONSTRAINT [Store_SM_RS] FOREIGN KEY ([store_id])  REFERENCES [Store]([store_id])
);
GO
-- ************************************** [Order]
CREATE TABLE [Order]
(
 [oder_id]   bigint NOT NULL ,
 [date]      date NOT NULL ,
 [member_id] bigint NOT NULL ,
 [status]    tinyint NOT NULL ,


 CONSTRAINT [Order_PK] PRIMARY KEY CLUSTERED ([oder_id] ASC),
 CONSTRAINT [Member_Order_RS] FOREIGN KEY ([member_id])  REFERENCES [Member]([member_id])
);
GO
-- ************************************** [Order_Detail]
CREATE TABLE [Order_Detail]
(
 [order_detail_id] bigint NOT NULL ,
 [quantity]        int NOT NULL ,
 [oder_id]         bigint NOT NULL ,
 [material_id]     bigint NOT NULL ,


 CONSTRAINT [OD_PK] PRIMARY KEY CLUSTERED ([order_detail_id] ASC),
 CONSTRAINT [OD_Material_RS] FOREIGN KEY ([material_id])  REFERENCES [Material]([material_id]),
 CONSTRAINT [Order_OD_RS] FOREIGN KEY ([oder_id])  REFERENCES [Order]([oder_id])
);
GO
