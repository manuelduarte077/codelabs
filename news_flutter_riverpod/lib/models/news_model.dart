import 'package:news_flutter_riverpod/models/models.dart';

class NewsModel {
  String? status;
  int? totalResults;
  List<Article>? articles;

  NewsModel({
    this.status,
    this.totalResults,
    this.articles,
  });

  NewsModel.fromJson(Map<String, dynamic> json) {
    status = json['status'];
    totalResults = json['totalResults'];
    if (json['articles'] != null) {
      articles = <Article>[];
      json['articles'].forEach((v) {
        articles!.add(Article.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['status'] = status;
    data['totalResults'] = totalResults;
    if (articles != null) {
      data['articles'] = articles!.map((v) => v.toJson()).toList();
    }

    return data;
  }
}
