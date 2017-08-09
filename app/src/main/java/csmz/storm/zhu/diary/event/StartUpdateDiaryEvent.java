package csmz.storm.zhu.diary.event;

import csmz.storm.zhu.diary.mode.DiaryBean;


public class StartUpdateDiaryEvent {

    private DiaryBean mDiaryBean;

    public StartUpdateDiaryEvent(DiaryBean diaryBean) {
        mDiaryBean = diaryBean;
    }

    public DiaryBean getDiaryBean() {
        return mDiaryBean;
    }
}
