Mô tả hệ thống
Hệ thống Quản lý Lịch trình Nhiệm vụ Đa người dùng sử dụng Đồ thị

Hệ thống này được thiết kế để quản lý lịch trình nhiệm vụ của nhiều người dùng, đảm bảo rằng không có sự trùng lặp giữa các nhiệm vụ của các người dùng khác nhau. Hệ thống sử dụng cấu trúc dữ liệu đồ thị (Graph) để biểu diễn các nhiệm vụ và mối quan hệ xung đột giữa chúng.

Các thành phần chính:
Người dùng (User): Mỗi người dùng có một ID duy nhất, tên, email, số điện thoại và mật khẩu được mã hóa.
Nhiệm vụ (Task): Mỗi nhiệm vụ được biểu diễn dưới dạng một đỉnh (vertex) trong đồ thị. Một nhiệm vụ bao gồm tên, nhãn, thời gian bắt đầu và kết thúc.
Đồ thị (Graph): Đồ thị biểu diễn các nhiệm vụ dưới dạng các đỉnh và các cạnh biểu diễn xung đột giữa các nhiệm vụ.
Quản lý lịch trình (ScheduleManagerSystem): Chứa các phương thức để thêm/xóa người dùng và nhiệm vụ, quản lý xung đột, và hiển thị lịch trình.
Chức năng chính:
Thêm/xóa người dùng: Quản lý danh sách người dùng.
Thêm/xóa nhiệm vụ: Quản lý các nhiệm vụ của từng người dùng và thêm các xung đột giữa các nhiệm vụ.
Hiển thị lịch trình: Hiển thị tất cả các nhiệm vụ, nhiệm vụ của một người dùng cụ thể, và lịch trình tổng thể với các nhiệm vụ không trùng lặp.
Thuật toán duyệt đồ thị: Sử dụng BFS, DFS để duyệt và tìm kiếm trong đồ thị.
Thuật toán Dijkstra: Tìm đường đi ngắn nhất giữa hai nhiệm vụ.
Thuật toán Prim: Tìm cây khung nhỏ nhất của đồ thị.
Đường đi Euler: Xác định các đỉnh bắt đầu của đường đi Euler trong đồ thị.
