//classDiagram
//    class MemberController {
//        +register()
//        +login()
//        +logout()
//        +memberList()
//        +updateMember()
//        +deleteMember()
//    }
//    
//    class ProductController {
//        +productList()
//        +searchByName()
//        +searchByPrice()
//        +registerProduct()
//        +updateProduct()
//        +deleteProduct()
//    }
//    
//    class BoardController {
//        +boardList()
//        +writePost()
//        +viewPost()
//        +updatePost()
//        +deletePost()
//        +comment()
//    }
//    
//    class LectureController {
//        +lectureList()
//        +registerLecture()
//        +enrollLecture()
//        +attendanceCheck()
//        +attendanceReport()
//    }
//    
//    class OrderController {
//        +orderProduct()
//        +orderList()
//        +orderDetails()
//        +updateOrderStatus()
//    }
//    
//    class MemberService {
//        +register()
//        +login()
//        +getMemberList()
//        +updateMember()
//        +deleteMember()
//    }
//    
//    class ProductService {
//        +getProductList()
//        +searchProducts()
//        +registerProduct()
//        +updateProduct()
//        +deleteProduct()
//    }
//    
//    class BoardService {
//        +getBoardList()
//        +writePost()
//        +getPost()
//        +updatePost()
//        +deletePost()
//        +addComment()
//    }
//    
//    class LectureService {
//        +getLectureList()
//        +registerLecture()
//        +enrollLecture()
//        +recordAttendance()
//        +getAttendanceReport()
//    }
//    
//    class OrderService {
//        +createOrder()
//        +getOrderList()
//        +getOrderDetails()
//        +updateOrderStatus()
//    }
//    
//    class MemberDAO {
//        +insert()
//        +select()
//        +selectAll()
//        +update()
//        +delete()
//    }
//    
//    class ProductDAO {
//        +insert()
//        +select()
//        +selectAll()
//        +selectByName()
//        +selectByPrice()
//        +update()
//        +delete()
//    }
//    
//    class BoardDAO {
//        +insert()
//        +select()
//        +selectAll()
//        +update()
//        +delete()
//        +insertComment()
//    }
//    
//    class LectureDAO {
//        +insert()
//        +select()
//        +selectAll()
//        +update()
//        +delete()
//        +insertEnrollment()
//        +insertAttendance()
//    }
//    
//    class OrderDAO {
//        +insert()
//        +select()
//        +selectAll()
//        +update()
//        +insertOrderDetail()
//    }
//    
//    MemberController --> MemberService
//    ProductController --> ProductService
//    BoardController --> BoardService
//    LectureController --> LectureService
//    OrderController --> OrderService
//    
//    MemberService --> MemberDAO
//    ProductService --> ProductDAO
//    BoardService --> BoardDAO
//    LectureService --> LectureDAO
//    OrderService --> OrderDAO