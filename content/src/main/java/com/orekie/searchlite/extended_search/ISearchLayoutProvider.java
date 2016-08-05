package com.orekie.searchlite.extended_search;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 请不要重命名这个文件，并且实现此接口，实现类的名字应为：com.orekie.searchlite.extended_search.SearchLayoutProvider
 */
public interface ISearchLayoutProvider {

    /**
     * 当 SearchLite 从 content provider 中得到你的数据，并且将其封装到一个通用的Model类数组中时，我将回调此方法
     * <p/>
     * 注意：
     * 直接在 xml 中关联的一切资源都会失效（@string/@drawable）
     * 请使用传入的 Resources 得到资源再设置一次
     * inflater 不能直接 inflate layout id ，请使用 resources.getLayout 方法获得 xmlParser 代替 id
     * inflate 的时候 viewGroup 传入 null 即可，我会后续处理 layout 不用担心
     *
     * @param resources 这是你的 app 内资源，它在 SearchLite 中反射创建
     * @param model     这是目标数据，数组大小为1-5，这取决于你的搜索类型
     * @param inflater  解析 xml 的 inflater
     * @return 根据 model 来 inflate 相应数量的 view 并绑定 model 显示
     */
    View[] getView(Resources resources, Model[] model, LayoutInflater inflater);
}
