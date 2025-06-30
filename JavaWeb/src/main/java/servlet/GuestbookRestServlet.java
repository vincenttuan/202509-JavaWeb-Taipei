package servlet;

/**
 * 提供訪客留言版的 RESTful API 並支援 JSON 格式資料儲存
 * 
 * API 路徑與用法
 * ------------------------------------------
 * 1. 取得全部留言
 * 	  GET  /api/guestbook
 * 2. 取得單筆留言
 * 	  GET  /api/guestbook/{id}
 * 3. 新增留言
 * 	  POST /api/guestbook
 * 	  Request Body (JSON): {"name": "John", "message": "abc"}
 * 4. 修改留言
 *    PUT  /api/guestbook/{id}
 * 	  Request Body (JSON): {"name": "Mary", "message": "def"}
 * 5. 刪除留言
 *    DELETE /api/guestbook/{id}
 * */

public class GuestbookRestServlet {

}
