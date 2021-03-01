USE [Fcinema]
GO
/****** Object:  Table [dbo].[cinema]    Script Date: 3/1/2021 3:20:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cinema](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_by] [varchar](255) NULL,
	[created_date] [datetime2](7) NULL,
	[modified_by] [varchar](255) NULL,
	[modified_date] [datetime2](7) NULL,
	[address] [nvarchar](255) NULL,
	[code] [varchar](255) NULL,
	[name] [nvarchar](255) NULL,
	[number_of_room] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_rlir8y0qi9ahqbuxbya8uiis3] UNIQUE NONCLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[disable_plan]    Script Date: 3/1/2021 3:20:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[disable_plan](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_by] [varchar](255) NULL,
	[created_date] [datetime2](7) NULL,
	[modified_by] [varchar](255) NULL,
	[modified_date] [datetime2](7) NULL,
	[code] [varchar](255) NULL,
	[end_time] [datetime2](7) NULL,
	[name] [varchar](255) NULL,
	[start_time] [datetime2](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[movie_categories]    Script Date: 3/1/2021 3:20:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[movie_categories](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_by] [varchar](255) NULL,
	[created_date] [datetime2](7) NULL,
	[modified_by] [varchar](255) NULL,
	[modified_date] [datetime2](7) NULL,
	[code] [varchar](255) NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_9o6ttp9p4nlfs3qy7fkp1niu6] UNIQUE NONCLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[movies]    Script Date: 3/1/2021 3:20:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[movies](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_by] [varchar](255) NULL,
	[created_date] [datetime2](7) NULL,
	[modified_by] [varchar](255) NULL,
	[modified_date] [datetime2](7) NULL,
	[cast] [varchar](255) NULL,
	[code] [varchar](255) NULL,
	[country] [varchar](255) NULL,
	[description] [varchar](max) NULL,
	[director] [varchar](255) NULL,
	[end_time] [datetime2](7) NULL,
	[image] [varchar](255) NULL,
	[language] [varchar](255) NULL,
	[length] [int] NULL,
	[name] [varchar](255) NULL,
	[number_of_tickets] [bigint] NULL,
	[rating] [int] NULL,
	[start_time] [datetime2](7) NULL,
	[status] [int] NULL,
	[trailer] [varchar](255) NULL,
	[year] [int] NULL,
	[movie_category_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_4ktyyw7m4lpx1bexlxujgpo73] UNIQUE NONCLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[payment]    Script Date: 3/1/2021 3:20:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[payment](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_by] [varchar](255) NULL,
	[created_date] [datetime2](7) NULL,
	[modified_by] [varchar](255) NULL,
	[modified_date] [datetime2](7) NULL,
	[card_number] [varchar](255) NULL,
	[cvv] [varchar](255) NULL,
	[expiration_date] [datetime2](7) NULL,
	[method] [varchar](255) NULL,
	[total] [bigint] NULL,
	[user_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[roles]    Script Date: 3/1/2021 3:20:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[roles](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_by] [varchar](255) NULL,
	[created_date] [datetime2](7) NULL,
	[modified_by] [varchar](255) NULL,
	[modified_date] [datetime2](7) NULL,
	[code] [int] NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[rooms]    Script Date: 3/1/2021 3:20:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[rooms](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_by] [varchar](255) NULL,
	[created_date] [datetime2](7) NULL,
	[modified_by] [varchar](255) NULL,
	[modified_date] [datetime2](7) NULL,
	[code] [varchar](255) NULL,
	[name] [varchar](255) NULL,
	[number_of_seat] [int] NULL,
	[status] [int] NULL,
	[cinema_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[schedules]    Script Date: 3/1/2021 3:20:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[schedules](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_by] [varchar](255) NULL,
	[created_date] [datetime2](7) NULL,
	[modified_by] [varchar](255) NULL,
	[modified_date] [datetime2](7) NULL,
	[code] [varchar](255) NULL,
	[end_time] [datetime2](7) NULL,
	[name] [varchar](255) NULL,
	[start_time] [datetime2](7) NULL,
	[movie_id] [bigint] NOT NULL,
	[room_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_je8c00m5g8tgi54gxbb8bwks0] UNIQUE NONCLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[seat]    Script Date: 3/1/2021 3:20:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[seat](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_by] [varchar](255) NULL,
	[created_date] [datetime2](7) NULL,
	[modified_by] [varchar](255) NULL,
	[modified_date] [datetime2](7) NULL,
	[code] [varchar](255) NULL,
	[name] [varchar](255) NULL,
	[status] [int] NULL,
	[room_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ticket_categories]    Script Date: 3/1/2021 3:20:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ticket_categories](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_by] [varchar](255) NULL,
	[created_date] [datetime2](7) NULL,
	[modified_by] [varchar](255) NULL,
	[modified_date] [datetime2](7) NULL,
	[code] [varchar](255) NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_nh2cw2hwiwp85gwdu974b3afg] UNIQUE NONCLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tickets]    Script Date: 3/1/2021 3:20:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tickets](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_by] [varchar](255) NULL,
	[created_date] [datetime2](7) NULL,
	[modified_by] [varchar](255) NULL,
	[modified_date] [datetime2](7) NULL,
	[code] [varchar](255) NULL,
	[expire_time] [datetime2](7) NULL,
	[gate] [varchar](255) NULL,
	[name] [varchar](255) NULL,
	[price] [bigint] NULL,
	[quantity] [bigint] NULL,
	[seat] [varchar](255) NULL,
	[start_time] [datetime2](7) NULL,
	[schedule_id] [bigint] NOT NULL,
	[ticket_category_id] [bigint] NULL,
	[user_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_e13ki8l4l6u599ociuljrqka9] UNIQUE NONCLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user_cinema]    Script Date: 3/1/2021 3:20:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_cinema](
	[user_id] [bigint] NOT NULL,
	[cinema_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[user_id] ASC,
	[cinema_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user_role]    Script Date: 3/1/2021 3:20:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_role](
	[user_id] [bigint] NOT NULL,
	[role_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[user_id] ASC,
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 3/1/2021 3:20:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_by] [varchar](255) NULL,
	[created_date] [datetime2](7) NULL,
	[modified_by] [varchar](255) NULL,
	[modified_date] [datetime2](7) NULL,
	[address] [varchar](255) NULL,
	[avatar] [varchar](255) NULL,
	[dob] [datetime2](7) NULL,
	[email] [varchar](255) NULL,
	[firstname] [varchar](255) NULL,
	[fullname] [varchar](255) NULL,
	[gender] [varchar](255) NULL,
	[lastname] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[phone] [varchar](255) NULL,
	[username] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_6dotkott2kjsp8vw4d0m25fb7] UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_r43af9ap4edm43mmtq01oddj6] UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[movies]  WITH CHECK ADD  CONSTRAINT [FK3h9sw5rslavs28iioynkeext1] FOREIGN KEY([movie_category_id])
REFERENCES [dbo].[movie_categories] ([id])
GO
ALTER TABLE [dbo].[movies] CHECK CONSTRAINT [FK3h9sw5rslavs28iioynkeext1]
GO
ALTER TABLE [dbo].[payment]  WITH CHECK ADD  CONSTRAINT [FKmi2669nkjesvp7cd257fptl6f] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[payment] CHECK CONSTRAINT [FKmi2669nkjesvp7cd257fptl6f]
GO
ALTER TABLE [dbo].[rooms]  WITH CHECK ADD  CONSTRAINT [FKtrp5tthblqudcutv31yyb21hq] FOREIGN KEY([cinema_id])
REFERENCES [dbo].[cinema] ([id])
GO
ALTER TABLE [dbo].[rooms] CHECK CONSTRAINT [FKtrp5tthblqudcutv31yyb21hq]
GO
ALTER TABLE [dbo].[schedules]  WITH CHECK ADD  CONSTRAINT [FK34r5t4jexlcas19pleifb8ihv] FOREIGN KEY([room_id])
REFERENCES [dbo].[rooms] ([id])
GO
ALTER TABLE [dbo].[schedules] CHECK CONSTRAINT [FK34r5t4jexlcas19pleifb8ihv]
GO
ALTER TABLE [dbo].[schedules]  WITH CHECK ADD  CONSTRAINT [FKrn994bufm9lvyguq5enr8pua2] FOREIGN KEY([movie_id])
REFERENCES [dbo].[movies] ([id])
GO
ALTER TABLE [dbo].[schedules] CHECK CONSTRAINT [FKrn994bufm9lvyguq5enr8pua2]
GO
ALTER TABLE [dbo].[seat]  WITH CHECK ADD  CONSTRAINT [FK6vlr4r6ab3yotlhhqxi0eepxd] FOREIGN KEY([room_id])
REFERENCES [dbo].[rooms] ([id])
GO
ALTER TABLE [dbo].[seat] CHECK CONSTRAINT [FK6vlr4r6ab3yotlhhqxi0eepxd]
GO
ALTER TABLE [dbo].[tickets]  WITH CHECK ADD  CONSTRAINT [FK4eqsebpimnjen0q46ja6fl2hl] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[tickets] CHECK CONSTRAINT [FK4eqsebpimnjen0q46ja6fl2hl]
GO
ALTER TABLE [dbo].[tickets]  WITH CHECK ADD  CONSTRAINT [FK9byj2xh170rnfmlit5sddwhbc] FOREIGN KEY([schedule_id])
REFERENCES [dbo].[schedules] ([id])
GO
ALTER TABLE [dbo].[tickets] CHECK CONSTRAINT [FK9byj2xh170rnfmlit5sddwhbc]
GO
ALTER TABLE [dbo].[tickets]  WITH CHECK ADD  CONSTRAINT [FKkrqxfo3bygn170i2mdsbt1of1] FOREIGN KEY([ticket_category_id])
REFERENCES [dbo].[ticket_categories] ([id])
GO
ALTER TABLE [dbo].[tickets] CHECK CONSTRAINT [FKkrqxfo3bygn170i2mdsbt1of1]
GO
ALTER TABLE [dbo].[user_cinema]  WITH CHECK ADD  CONSTRAINT [FK5y9a5xy07y6gv10xkqg2w0i95] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[user_cinema] CHECK CONSTRAINT [FK5y9a5xy07y6gv10xkqg2w0i95]
GO
ALTER TABLE [dbo].[user_cinema]  WITH CHECK ADD  CONSTRAINT [FKs1qfbas5byya7m02bnlt9gbg4] FOREIGN KEY([cinema_id])
REFERENCES [dbo].[cinema] ([id])
GO
ALTER TABLE [dbo].[user_cinema] CHECK CONSTRAINT [FKs1qfbas5byya7m02bnlt9gbg4]
GO
ALTER TABLE [dbo].[user_role]  WITH CHECK ADD  CONSTRAINT [FKj345gk1bovqvfame88rcx7yyx] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[user_role] CHECK CONSTRAINT [FKj345gk1bovqvfame88rcx7yyx]
GO
ALTER TABLE [dbo].[user_role]  WITH CHECK ADD  CONSTRAINT [FKt7e7djp752sqn6w22i6ocqy6q] FOREIGN KEY([role_id])
REFERENCES [dbo].[roles] ([id])
GO
ALTER TABLE [dbo].[user_role] CHECK CONSTRAINT [FKt7e7djp752sqn6w22i6ocqy6q]
GO
