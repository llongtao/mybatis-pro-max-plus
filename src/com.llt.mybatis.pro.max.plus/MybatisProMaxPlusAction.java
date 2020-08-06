package com.llt.mybatis.pro.max.plus;

import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.module.ModuleWithNameAlreadyExists;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.util.KeyedExtensionFactory;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiElementFactory;
import com.intellij.util.pico.CachingConstructorInjectionComponentAdapter;
import com.llt.mybatishelper.core.utils.StringUtils;
import org.jdom.JDOMException;

import java.io.IOException;

/**
 * @author LILONGTAO
 */
public class MybatisProMaxPlusAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {


        MainDialog mainDialog = new MainDialog(e.getProject());
        //是否允许用户通过拖拽的方式扩大或缩小你的表单框，我这里定义为true，表示允许
        mainDialog.setResizable(false);
        mainDialog.show();

    }
}
