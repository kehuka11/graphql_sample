import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    vus: 10,  // 仮想ユーザー数
    duration: '20s',  // テストの実行時間
};

export default function () {
    const url = 'http://localhost:8080/graphql';  // GraphQLエンドポイント

    // GraphQL Mutationのペイロード
    const payload = JSON.stringify({
        query: `
            mutation {
                registerBookV2(title: "kk", author: { id: "author-1", firstName: "Scott", lastName: "Burn" }) {
                    id
                    title
                    author {
                        id
                        firstName
                        lastName
                    }
                }
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
