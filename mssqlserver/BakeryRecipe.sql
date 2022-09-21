--USE master;
--ALTER DATABASE BakeryRecipe SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
--DROP DATABASE BakeryRecipe ;
--GO


create database BakeryRecipe
go
use BakeryRecipe
go

-- ************************************** [Member]
CREATE TABLE [Member]
(
 [member_id] bigint IDENTITY (1, 1) NOT NULL ,
 [name]      varchar(50) NOT NULL ,
 [email]     varchar(50) NOT NULL ,
 [DOB]       date NOT NULL ,
 [username]  varchar(50) NOT NULL ,
 [password]  varchar(70) NOT NULL ,
 [avatar]   varchar(50) NULL ,


 CONSTRAINT [Role_User_PK] PRIMARY KEY CLUSTERED ([member_id] ASC)
);
GO
-- ************************************** [Role]
CREATE TABLE [Role]
(
 [role_id]   int IDENTITY (1, 1) NOT NULL ,
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
 CONSTRAINT [Member_UR_FK] FOREIGN KEY ([member_id])  REFERENCES [Member]([member_id]),
 CONSTRAINT [Role_UR_FK] FOREIGN KEY ([role_id])  REFERENCES [Role]([role_id])
);
GO

-- ************************************** [Friend]
CREATE TABLE [Friend]
(
 [member_id] bigint IDENTITY (1, 1) NOT NULL ,
 [frend_id]  bigint NOT NULL ,
 [status]    smallint NOT NULL ,


 CONSTRAINT [Friend_PK] PRIMARY KEY CLUSTERED ([member_id] ASC, [frend_id] ASC),
 CONSTRAINT [Member_Friend_FK] FOREIGN KEY ([member_id])  REFERENCES [Member]([member_id]),
 CONSTRAINT [Member_Friend_FK_2] FOREIGN KEY ([frend_id])  REFERENCES [Member]([member_id])
);
GO
-- ************************************** [Message]
CREATE TABLE [Message]
(
 [massage_id]         bigint IDENTITY (1, 1) NOT NULL ,
 [massage_body]       nvarchar(500) NOT NULL ,
 [member_sender_id]   bigint NOT NULL ,
 [member_receiver_id] bigint NOT NULL ,
 [mss_create_date]    datetime NOT NULL ,


 CONSTRAINT [Massage_PK] PRIMARY KEY CLUSTERED ([massage_id] ASC),
 CONSTRAINT [Member_Message_Receiver_FK] FOREIGN KEY ([member_receiver_id])  REFERENCES [Member]([member_id]),
 CONSTRAINT [Member_Message_Sender_FK] FOREIGN KEY ([member_sender_id])  REFERENCES [Member]([member_id])
);
GO
-- ************************************** [Post]
CREATE TABLE [Post]
(
 [post_id]     bigint IDENTITY (1, 1) NOT NULL ,
 [member_id]   bigint NOT NULL ,
 [image]       varchar(50) NULL ,
 [video]       varchar(50) NULL ,
 [create_date] datetime NOT NULL ,
 [tool]        nvarchar(500) NOT NULL ,
 [steps]       nvarchar(3000) NOT NULL ,


 CONSTRAINT [Post_PK] PRIMARY KEY CLUSTERED ([post_id] ASC),
 CONSTRAINT [User_Post_FK] FOREIGN KEY ([member_id])  REFERENCES [Member]([member_id])
);
GO
-- ************************************** [Post_Image]
CREATE TABLE [Post_Image]
(
 [post_image_id] bigint IDENTITY (1, 1) NOT NULL ,
 [post_id]       bigint NOT NULL ,
 [image]         varchar(50) NOT NULL ,


 CONSTRAINT [Post_Image_PK] PRIMARY KEY CLUSTERED ([post_image_id] ASC),
 CONSTRAINT [Post_PI_FK] FOREIGN KEY ([post_id])  REFERENCES [Post]([post_id])
);
GO
-- ************************************** [Post_Video]
CREATE TABLE [Post_Video]
(
 [post_video_id] bigint IDENTITY (1, 1) NOT NULL ,
 [post_id]       bigint NOT NULL ,
 [video]         varchar(50) NOT NULL ,


 CONSTRAINT [Post_Video_PK] PRIMARY KEY CLUSTERED ([post_video_id] ASC),
 CONSTRAINT [Post_PV_FK] FOREIGN KEY ([post_id])  REFERENCES [Post]([post_id])
);
GO
-- ************************************** [Comment]
CREATE TABLE [Comment]
(
 [comment_id]     bigint IDENTITY (1, 1) NOT NULL ,
 [member_id]      bigint NOT NULL ,
 [post_id]        bigint NOT NULL ,
 [comment_detail] nvarchar(500) NOT NULL ,
 [image]          varchar(50) NULL ,
 [video]          varchar(50) NULL ,


 CONSTRAINT [Commnet_PK] PRIMARY KEY CLUSTERED ([comment_id] ASC),
 CONSTRAINT [Post_Comment_FK] FOREIGN KEY ([post_id])  REFERENCES [Post]([post_id]),
 CONSTRAINT [Member_Comment_FK] FOREIGN KEY ([member_id])  REFERENCES [Member]([member_id])
);
GO
-- ************************************** [Food]
CREATE TABLE [Food]
(
 [post_id]     bigint IDENTITY (1, 1) NOT NULL ,
 [name]        nvarchar(50) NOT NULL ,
 [description] nvarchar(500) NOT NULL ,


 CONSTRAINT [Food_PK] PRIMARY KEY CLUSTERED ([post_id] ASC),
 CONSTRAINT [Post_Food_FK] FOREIGN KEY ([post_id])  REFERENCES [Post]([post_id])
);
GO
-- ************************************** [Material]
CREATE TABLE [Material]
(
 [material_id]   bigint IDENTITY (1, 1) NOT NULL ,
 [material_name] nvarchar(50) NOT NULL ,
 [description]   nvarchar(500) NOT NULL ,
 [Unit]          varchar(50) NOT NULL ,


 CONSTRAINT [Material_PK] PRIMARY KEY CLUSTERED ([material_id] ASC)
);
GO
-- ************************************** [Post_Material]
CREATE TABLE [Post_Material]
(
 [post_id]     bigint NOT NULL ,
 [material_id] bigint NOT NULL ,
 [quantity]    smallint NOT NULL ,


 CONSTRAINT [Post_Matarial_PK] PRIMARY KEY CLUSTERED ([post_id] ASC, [material_id] ASC),
 CONSTRAINT [Material_MD_FK] FOREIGN KEY ([material_id])  REFERENCES [Material]([material_id]),
 CONSTRAINT [Post_MD_FK] FOREIGN KEY ([post_id])  REFERENCES [Post]([post_id])
);
GO
-- ************************************** [Store]
CREATE TABLE [Store]
(
 [store_id]   bigint IDENTITY (1, 1) NOT NULL ,
 [store_name] varchar(50) NOT NULL ,
 [member_id]  bigint NOT NULL ,
 [address]    nvarchar(500) NOT NULL ,
 [phone]      varchar(50) NOT NULL ,


 CONSTRAINT [Store_PK] PRIMARY KEY CLUSTERED ([store_id] ASC),
 CONSTRAINT [Menber_Store_FK] FOREIGN KEY ([member_id])  REFERENCES [Member]([member_id])
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
 CONSTRAINT [Material_SM_FK] FOREIGN KEY ([material_id])  REFERENCES [Material]([material_id]),
 CONSTRAINT [Store_SM_FK] FOREIGN KEY ([store_id])  REFERENCES [Store]([store_id])
);
GO
-- ************************************** [Order]
CREATE TABLE [Order]
(
 [oder_id]   bigint IDENTITY (1, 1) NOT NULL ,
 [date]      date NOT NULL ,
 [member_id] bigint NOT NULL ,
 [status]    tinyint NOT NULL ,


 CONSTRAINT [Order_PK] PRIMARY KEY CLUSTERED ([oder_id] ASC),
 CONSTRAINT [Member_Order_FK] FOREIGN KEY ([member_id])  REFERENCES [Member]([member_id])
);
GO
-- ************************************** [Order_Detail]
CREATE TABLE [Order_Detail]
(
 [order_detail_id] bigint IDENTITY (1, 1) NOT NULL ,
 [quantity]        int NOT NULL ,
 [oder_id]         bigint NOT NULL ,
 [material_id]     bigint NOT NULL ,


 CONSTRAINT [OD_PK] PRIMARY KEY CLUSTERED ([order_detail_id] ASC),
 CONSTRAINT [OD_Material_FK] FOREIGN KEY ([material_id])  REFERENCES [Material]([material_id]),
 CONSTRAINT [Order_OD_FK] FOREIGN KEY ([oder_id])  REFERENCES [Order]([oder_id])
);
GO

insert into dbo.Member (member_id, name, email, DOB, username, password, avatar)
values  (3, N'user', N'user@gmail.com', N'2001-10-17', N'user', N'$2a$10$Ok1e.GgYAsCWbvqX9hVaZOvDgs5UuCLVt45ufhxUOa.hyw8TytjT6', null),
        (4, N'user2', N'user@gmail.com', N'2001-10-17', N'user2', N'$2a$10$xXPiBG6IrTBOHv86TZ8fo.uQKraLOwi1pAp8C2kyiqsDoZocF1Wwe', null);
GO

insert into dbo.Role (role_id, role_name)
values  (1, N'ADMIN'),
        (2, N'USER');
GO

insert into dbo.Member_Role (member_id, role_id)
values  (3, 2),
        (4, 2);
GO