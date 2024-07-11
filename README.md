<h1>Mô tả hệ thống</h1>
<h2>Hệ thống Quản lý Lịch trình Nhiệm vụ Đa người dùng sử dụng Đồ thị</h2>

Hệ thống này được thiết kế để quản lý lịch trình nhiệm vụ của nhiều người dùng, đảm bảo rằng không có sự trùng lặp giữa các nhiệm vụ của các người dùng khác nhau. Hệ thống sử dụng cấu trúc dữ liệu đồ thị (Graph) để biểu diễn các nhiệm vụ và mối quan hệ xung đột giữa chúng.

<h3>Các thành phần chính:</h3>

<b>Người dùng (User):</b> Mỗi người dùng có một ID duy nhất, tên, email, số điện thoại và mật khẩu được mã hóa.

<b>Nhiệm vụ (Task):</b> Mỗi nhiệm vụ được biểu diễn dưới dạng một đỉnh (vertex) trong đồ thị. Một nhiệm vụ bao gồm tên, nhãn, thời gian bắt đầu và kết thúc.

<b>Đồ thị (Graph):</b> Đồ thị biểu diễn các nhiệm vụ dưới dạng các đỉnh và các cạnh biểu diễn xung đột giữa các nhiệm vụ.

<b>Quản lý lịch trình (ScheduleManagerSystem):</b> Chứa các phương thức để thêm/xóa người dùng và nhiệm vụ, quản lý xung đột, và hiển thị lịch trình.

<h3>Chức năng chính:</h3>

<b>Thêm/xóa người dùng:</b> Quản lý danh sách người dùng.

<b>Thêm/xóa nhiệm vụ:</b> Quản lý các nhiệm vụ của từng người dùng và thêm các xung đột giữa các nhiệm vụ.

<b>Hiển thị lịch trình:</b> Hiển thị tất cả các nhiệm vụ, nhiệm vụ của một người dùng cụ thể, và lịch trình tổng thể với các nhiệm vụ không trùng lặp.

<b>Thuật toán duyệt đồ thị:</b> Sử dụng BFS, DFS để duyệt và tìm kiếm trong đồ thị.

<b>Thuật toán Dijkstra:</b> Tìm đường đi ngắn nhất giữa hai nhiệm vụ.

<b>Thuật toán Prim:</b> Tìm cây khung nhỏ nhất của đồ thị.

<b>Đường đi Euler:</b> Xác định các đỉnh bắt đầu của đường đi Euler trong đồ thị.
