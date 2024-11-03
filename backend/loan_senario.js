import http from 'k6/http';

// 外部ファイル 'loan_data.json' から配列データを読み込み
const data = new SharedArray('loan data', function () {
  return JSON.parse(open('./loan_data.json')); 
});

export default function () {

    const url = 'http://localhost:8080/graphql';  // GraphQLエンドポイント

    data.forEach(item => {
        for (let i = 0; i < 10; i++) {

            const payload = JSON.stringify({
                query: `
                    mutation {
                        loan(bookId: "${item.bookId}", userId: "${item.userId}")
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

        }
    });
}
