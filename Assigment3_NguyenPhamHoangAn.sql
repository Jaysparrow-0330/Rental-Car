USE [master]
GO
/****** Object:  Database [Assignment3_NguyenPhamHoangAn]    Script Date: 3/7/2021 4:22:00 PM ******/
CREATE DATABASE [Assignment3_NguyenPhamHoangAn]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Assignment3_NguyenPhamHoangAn', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Assignment3_NguyenPhamHoangAn.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Assignment3_NguyenPhamHoangAn_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Assignment3_NguyenPhamHoangAn_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Assignment3_NguyenPhamHoangAn].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET ARITHABORT OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET RECOVERY FULL 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET  MULTI_USER 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'Assignment3_NguyenPhamHoangAn', N'ON'
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET QUERY_STORE = OFF
GO
USE [Assignment3_NguyenPhamHoangAn]
GO
/****** Object:  Table [dbo].[tblCar]    Script Date: 3/7/2021 4:22:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCar](
	[carID] [int] IDENTITY(1,1) NOT NULL,
	[carName] [nvarchar](50) NOT NULL,
	[quantity] [int] NOT NULL,
	[price] [float] NOT NULL,
	[color] [nvarchar](50) NOT NULL,
	[year] [int] NOT NULL,
	[description] [nvarchar](max) NOT NULL,
	[categoryID] [nvarchar](50) NOT NULL,
	[status] [bit] NOT NULL,
	[imgLink] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_tblCar] PRIMARY KEY CLUSTERED 
(
	[carID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblCategory]    Script Date: 3/7/2021 4:22:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCategory](
	[categoryID] [nvarchar](50) NOT NULL,
	[categoryName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_tblCategory] PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblDiscount]    Script Date: 3/7/2021 4:22:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblDiscount](
	[discountID] [nvarchar](50) NOT NULL,
	[percentValue] [int] NOT NULL,
	[discountName] [nvarchar](50) NOT NULL,
	[startDate] [date] NOT NULL,
	[endDate] [date] NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_tblDiscount] PRIMARY KEY CLUSTERED 
(
	[discountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblfeedback]    Script Date: 3/7/2021 4:22:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblfeedback](
	[feedbackID] [int] IDENTITY(1,1) NOT NULL,
	[categoryID] [nvarchar](50) NOT NULL,
	[email] [nvarchar](100) NOT NULL,
	[point] [int] NOT NULL,
	[carID] [int] NOT NULL,
 CONSTRAINT [PK_tblfeedback] PRIMARY KEY CLUSTERED 
(
	[feedbackID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrder]    Script Date: 3/7/2021 4:22:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrder](
	[orderID] [int] IDENTITY(1,1) NOT NULL,
	[email] [nvarchar](100) NOT NULL,
	[totalPrice] [float] NOT NULL,
	[orderDate] [date] NOT NULL,
	[discountID] [nvarchar](50) NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_tblOrder] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrderDetail]    Script Date: 3/7/2021 4:22:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrderDetail](
	[orderDetailID] [int] IDENTITY(1,1) NOT NULL,
	[orderID] [int] NOT NULL,
	[carID] [int] NOT NULL,
	[startDate] [date] NOT NULL,
	[endDate] [date] NOT NULL,
	[price] [float] NOT NULL,
	[quantity] [int] NOT NULL,
	[imgLink] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_tblOrderDetail] PRIMARY KEY CLUSTERED 
(
	[orderDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 3/7/2021 4:22:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUser](
	[email] [nvarchar](100) NOT NULL,
	[userPassword] [nvarchar](50) NOT NULL,
	[userName] [nvarchar](50) NOT NULL,
	[userCode] [nvarchar](50) NOT NULL,
	[phone] [nvarchar](50) NOT NULL,
	[address] [nvarchar](max) NOT NULL,
	[createDate] [date] NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_tblUser] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[tblCar] ON 

INSERT [dbo].[tblCar] ([carID], [carName], [quantity], [price], [color], [year], [description], [categoryID], [status], [imgLink]) VALUES (1, N'Rolls Royce wraith 2021', 5, 5000, N'black', 2021, N'The Rolls-Royce Wraith is a Full-size luxury car', N'LUX', 1, N'https://i.redd.it/81lm4v0nqo201.jpg')
INSERT [dbo].[tblCar] ([carID], [carName], [quantity], [price], [color], [year], [description], [categoryID], [status], [imgLink]) VALUES (2, N'Rolls-Royce Cullinan', 3, 7500, N'black', 2020, N'The Rolls-Royce Cullinan is a full-sized luxury sport utility wagon', N'LUX', 1, N'https://www.bugattigoldcoast.com/galleria_images/8234/8234_main_f.jpg')
INSERT [dbo].[tblCar] ([carID], [carName], [quantity], [price], [color], [year], [description], [categoryID], [status], [imgLink]) VALUES (3, N'Rolls Royce ghost', 8, 6000, N'black', 2020, N', the new Ghost is a motor car for those who recognise beauty in restraint', N'LUX', 1, N'https://cdn.lifestyleasia.com/wp-content/uploads/2019/04/08183948/rolls-royce-ghost-black-badge-versace-11-e1554720138603.jpg')
INSERT [dbo].[tblCar] ([carID], [carName], [quantity], [price], [color], [year], [description], [categoryID], [status], [imgLink]) VALUES (4, N'1930 Rolls Royce Vintage Elegance', 1, 49999, N'black', 1930, N'Rolls Royce Vintage Elegance beautiful restored Rolls in all it''s glory. Found in North Carolina', N'VIN', 1, N'https://i.pinimg.com/564x/51/8c/2b/518c2b9a3ebc0939d970d5d8440382a4.jpg')
INSERT [dbo].[tblCar] ([carID], [carName], [quantity], [price], [color], [year], [description], [categoryID], [status], [imgLink]) VALUES (5, N'Ford Mustang', 2, 50000, N'black', 1969, N'Old Vintage Mustang still muscle', N'VIN', 1, N'https://i.pinimg.com/564x/a9/7a/0b/a97a0bb029a538dfd459f73483422f16.jpg')
INSERT [dbo].[tblCar] ([carID], [carName], [quantity], [price], [color], [year], [description], [categoryID], [status], [imgLink]) VALUES (6, N'Lamborghini Huracán', 12, 8000, N'black', 2015, N'The Lamborghini Huracán is the perfect fusion of technology and design', N'SUP', 1, N'https://i.pinimg.com/736x/8c/a6/ae/8ca6ae2d3db05fdffb77faba75d2c0ff.jpg')
INSERT [dbo].[tblCar] ([carID], [carName], [quantity], [price], [color], [year], [description], [categoryID], [status], [imgLink]) VALUES (7, N'Lamborghini Aventador SVJ', 12, 10000, N'black', 2019, N'FEEL THE ENGINE. Listen to the 770 CV naturally aspirated V12 engine waiting to be tamed. Capable of speeds greater than 217 mph (350 km/h)', N'SUP', 1, N'https://exclusivecarregistry.com/server/php/files-2/584301210719_wMusvd7.jpeg')
INSERT [dbo].[tblCar] ([carID], [carName], [quantity], [price], [color], [year], [description], [categoryID], [status], [imgLink]) VALUES (9, N'Ferrari F8 Tributo', 12, 14000, N'black', 2020, N'The Ferrari F8 Tributo is the new mid-rear-engined sports car that represents the highest expression of the Prancing Horse''s classic two-seater berlinetta', N'SUP', 1, N'https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-9/108724372_3506367392762638_5227445283518492401_o.jpg?_nc_cat=101&ccb=1-3&_nc_sid=a26aad&_nc_ohc=gkGI866KHgMAX_XQrJb&_nc_ht=scontent.fsgn5-1.fna&oh=fc527ef5349a0e348f996b7fa1fb008c&oe=60694875')
INSERT [dbo].[tblCar] ([carID], [carName], [quantity], [price], [color], [year], [description], [categoryID], [status], [imgLink]) VALUES (10, N'Bentley Continental GT', 4, 6000, N'black', 2010, N'The Continental GT V8 puts the power of Bentley''s responsive and agile V8 engine in your hands', N'LUX', 1, N'https://cutewallpaper.org/21/black-bentley-wallpapers/Bentley-Continental-GT-Speed-Wallpapers-Wallpaper-Cave-.jpg')
INSERT [dbo].[tblCar] ([carID], [carName], [quantity], [price], [color], [year], [description], [categoryID], [status], [imgLink]) VALUES (11, N'Rollls Royce Phantom Gold 24k', 1, 78000, N'black gold', 2015, N'The Golden One , most expensive Luxury car', N'LUX', 1, N'https://i.pinimg.com/originals/99/4e/26/994e26629a367f2a7da39539a93e27ee.jpg')
SET IDENTITY_INSERT [dbo].[tblCar] OFF
GO
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'CLS', N'Classic Car')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'LUX', N'Luxury Car')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'SUP', N'Super Car')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'VIN', N'Vintage Car')
GO
INSERT [dbo].[tblDiscount] ([discountID], [percentValue], [discountName], [startDate], [endDate], [status]) VALUES (N'NONE', 0, N'NONE', CAST(N'2020-06-03' AS Date), CAST(N'2020-06-03' AS Date), 1)
INSERT [dbo].[tblDiscount] ([discountID], [percentValue], [discountName], [startDate], [endDate], [status]) VALUES (N'RICHMAN', 50, N'For Richman', CAST(N'2021-03-07' AS Date), CAST(N'2021-10-03' AS Date), 1)
GO
SET IDENTITY_INSERT [dbo].[tblOrder] ON 

INSERT [dbo].[tblOrder] ([orderID], [email], [totalPrice], [orderDate], [discountID], [status]) VALUES (3, N'jack3460@gmail.com', 415000, CAST(N'2021-03-07' AS Date), N'NONE', 1)
INSERT [dbo].[tblOrder] ([orderID], [email], [totalPrice], [orderDate], [discountID], [status]) VALUES (4, N'jack3460@gmail.com', 390000, CAST(N'2021-03-07' AS Date), N'RICHMAN', 1)
INSERT [dbo].[tblOrder] ([orderID], [email], [totalPrice], [orderDate], [discountID], [status]) VALUES (5, N'jack3460@gmail.com', 390000, CAST(N'2021-03-07' AS Date), N'RICHMAN', 0)
INSERT [dbo].[tblOrder] ([orderID], [email], [totalPrice], [orderDate], [discountID], [status]) VALUES (6, N'jack3460@gmail.com', 56000, CAST(N'2021-03-07' AS Date), N'RICHMAN', 1)
INSERT [dbo].[tblOrder] ([orderID], [email], [totalPrice], [orderDate], [discountID], [status]) VALUES (7, N'jack3460@gmail.com', 384000, CAST(N'2021-03-07' AS Date), N'RICHMAN', 1)
INSERT [dbo].[tblOrder] ([orderID], [email], [totalPrice], [orderDate], [discountID], [status]) VALUES (8, N'jack3460@gmail.com', 899982, CAST(N'2021-03-07' AS Date), N'NONE', 1)
SET IDENTITY_INSERT [dbo].[tblOrder] OFF
GO
SET IDENTITY_INSERT [dbo].[tblOrderDetail] ON 

INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [carID], [startDate], [endDate], [price], [quantity], [imgLink]) VALUES (1, 5, 11, CAST(N'2021-03-08' AS Date), CAST(N'2021-03-12' AS Date), 78000, 1, N'https://i.pinimg.com/originals/99/4e/26/994e26629a367f2a7da39539a93e27ee.jpg')
INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [carID], [startDate], [endDate], [price], [quantity], [imgLink]) VALUES (2, 6, 9, CAST(N'2021-03-09' AS Date), CAST(N'2021-03-12' AS Date), 14000, 1, N'https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-9/108724372_3506367392762638_5227445283518492401_o.jpg?_nc_cat=101&ccb=1-3&_nc_sid=a26aad&_nc_ohc=gkGI866KHgMAX_XQrJb&_nc_ht=scontent.fsgn5-1.fna&oh=fc527ef5349a0e348f996b7fa1fb008c&oe=60694875')
INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [carID], [startDate], [endDate], [price], [quantity], [imgLink]) VALUES (3, 7, 11, CAST(N'2021-03-09' AS Date), CAST(N'2021-03-12' AS Date), 78000, 1, N'https://i.pinimg.com/originals/99/4e/26/994e26629a367f2a7da39539a93e27ee.jpg')
INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [carID], [startDate], [endDate], [price], [quantity], [imgLink]) VALUES (4, 7, 3, CAST(N'2021-03-09' AS Date), CAST(N'2021-03-12' AS Date), 6000, 3, N'https://cdn.lifestyleasia.com/wp-content/uploads/2019/04/08183948/rolls-royce-ghost-black-badge-versace-11-e1554720138603.jpg')
INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [carID], [startDate], [endDate], [price], [quantity], [imgLink]) VALUES (5, 8, 4, CAST(N'2021-03-08' AS Date), CAST(N'2021-03-25' AS Date), 49999, 1, N'https://i.pinimg.com/564x/51/8c/2b/518c2b9a3ebc0939d970d5d8440382a4.jpg')
SET IDENTITY_INSERT [dbo].[tblOrderDetail] OFF
GO
INSERT [dbo].[tblUser] ([email], [userPassword], [userName], [userCode], [phone], [address], [createDate], [status]) VALUES (N'annphse140542@fpt.edu.vn', N'1', N'annguyern', N'JnQkYM', N'0123456783', N'asdfghj', CAST(N'2021-03-06' AS Date), 1)
INSERT [dbo].[tblUser] ([email], [userPassword], [userName], [userCode], [phone], [address], [createDate], [status]) VALUES (N'datplqse140544@fpt.edu.vn', N'1', N'dat', N'CmrZ6P', N'0123456783', N'asdfghj', CAST(N'2021-03-07' AS Date), 0)
INSERT [dbo].[tblUser] ([email], [userPassword], [userName], [userCode], [phone], [address], [createDate], [status]) VALUES (N'hienlu2010vn@gmail.com', N'1', N'HienLu', N'pSkFpW', N'0123456783', N'asdfghj', CAST(N'2021-03-07' AS Date), 1)
INSERT [dbo].[tblUser] ([email], [userPassword], [userName], [userCode], [phone], [address], [createDate], [status]) VALUES (N'jack3460@gmail.com', N'123', N'JaySparrow', N'qdasda', N'12345676789', N'ewerhgthjfgjg', CAST(N'2021-05-03' AS Date), 1)
INSERT [dbo].[tblUser] ([email], [userPassword], [userName], [userCode], [phone], [address], [createDate], [status]) VALUES (N'nguyenthanhliem1708@gmail.com', N'1', N'Liem', N'sAuxuu', N'03746583461', N'q33', CAST(N'2021-03-07' AS Date), 1)
GO
ALTER TABLE [dbo].[tblCar]  WITH CHECK ADD  CONSTRAINT [FK_tblCar_tblCategory] FOREIGN KEY([categoryID])
REFERENCES [dbo].[tblCategory] ([categoryID])
GO
ALTER TABLE [dbo].[tblCar] CHECK CONSTRAINT [FK_tblCar_tblCategory]
GO
ALTER TABLE [dbo].[tblfeedback]  WITH CHECK ADD  CONSTRAINT [FK_tblfeedback_tblCar] FOREIGN KEY([carID])
REFERENCES [dbo].[tblCar] ([carID])
GO
ALTER TABLE [dbo].[tblfeedback] CHECK CONSTRAINT [FK_tblfeedback_tblCar]
GO
ALTER TABLE [dbo].[tblfeedback]  WITH CHECK ADD  CONSTRAINT [FK_tblfeedback_tblUser] FOREIGN KEY([email])
REFERENCES [dbo].[tblUser] ([email])
GO
ALTER TABLE [dbo].[tblfeedback] CHECK CONSTRAINT [FK_tblfeedback_tblUser]
GO
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD  CONSTRAINT [FK_tblOrder_tblDiscount] FOREIGN KEY([discountID])
REFERENCES [dbo].[tblDiscount] ([discountID])
GO
ALTER TABLE [dbo].[tblOrder] CHECK CONSTRAINT [FK_tblOrder_tblDiscount]
GO
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD  CONSTRAINT [FK_tblOrder_tblUser] FOREIGN KEY([email])
REFERENCES [dbo].[tblUser] ([email])
GO
ALTER TABLE [dbo].[tblOrder] CHECK CONSTRAINT [FK_tblOrder_tblUser]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetail_tblOrder] FOREIGN KEY([orderID])
REFERENCES [dbo].[tblOrder] ([orderID])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK_tblOrderDetail_tblOrder]
GO
USE [master]
GO
ALTER DATABASE [Assignment3_NguyenPhamHoangAn] SET  READ_WRITE 
GO
