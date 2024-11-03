import http from 'k6/http';

// 外部ファイル 'return_history_data.json' から配列データを読み込み
const data = new SharedArray('return history data', function () {
  return JSON.parse(open('./return_history_data.json')); 
});

export default function () {

    const url = 'http://localhost:8080/graphql';  // GraphQLエンドポイント

    data.forEach(item => {
        

            const payload = JSON.stringify({
                query: `
                    mutation {
                        returnBook(bookId: "${item.bookId}", userId: "${item.userId}", loanId: "${item.loanId}")
                    }
                `,
            });

            // ヘッダー設定（JSON形式でリクエストを送る）
            const params = {
                headers: {
                    'Content-Type': 'application/json',
                },
            };

            // POSTリクエストでGraphQLのMutationを送信
            const res = http.post(url, payload, params);

            // レスポンスのステータスコードが200であるかをチェック
            check(res, {
                'status was 200': (r) => r.status === 200,
            });

    });
}
