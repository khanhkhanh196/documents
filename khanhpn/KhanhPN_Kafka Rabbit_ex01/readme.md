



run the publisher
send message to queue with PostMan

run: http://localhost:8082/admin/order/sapoex/{id} to send message to SapoExpress Queue
id from 1 to 4

run: http://localhost:8082/admin/order/ghtk/{id} to send message to GHTK Queue
id from 1 to 4

1. Có bao nhiêu loại exchange chính trong rabbitmq
   
   Đáp án: 4
   
2. Giả sử có 1 topic exchange và queue được binding với nhau theo pattern
   “sapo.#.logs” thì những message nào sẽ match vs pattern kia ( chọn nhiều)
   
   Đáp án: C. sapo.core.web.logs và D. sapo.core.logs
   
3. Giả sử có 1 topic exchange và queue được binding với nhau theo pattern
   “sapo.*.logs” thì những message nào sẽ match vs pattern kia ( chọn nhiều)
   
   Đáp án: D. sapo.core.logs
   
4. Message được gửi vào Exchange nhưng ko được route tới Queue nào thì sẽ
   ra sao?
   
   Đáp án: message gửi vào Exchange nhưng không vào Queue nào sẽ tự động bị hủy. RabbitMQ
   cung cấp một cơ chế để collect các message không thể gửi được trước khi chúng bị hủy
   
5. Số consumer tối đa xử lý tối đa tại một thời điểm là bao nhiêu?
   
   Đáp án: tùy vào thuộc tính concurency được set-up
   
6. Message được publisher gửi vào đâu
   
   Đáp án: A. Exchange 
   
7. Giả sử có 2 exchange A,B và 2 queue X,Y . Exchange A được Binding với
   X, B với Y . Có thể cấu hình binding giữa B và X hay khônng? Vì sao.
   
   Đáp án: Có thể vì 1 Queue có thể bind với nhiều exchange.

8. Giả sử consumer đang xử lý message chưa xong, ta ngắt kết nối giữa server
   và client thì message có còn trên rabbitmq không, vì sao ?

   Đáp án: Message chỉ bị xoá khỏi queue khi consumer báo lại cho broker là xử lý thành công, 
   nếu thất bại hoặc mất kết nối → message được trả về queue để gửi cho consumer khác 
   => message có còn trên rabbitmq