package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.araw.models.Submission
import com.kirkbushman.araw.models.general.Vote
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.TestApplication
import com.kirkbushman.sampleapp.controllers.SubmissionController
import com.kirkbushman.sampleapp.doAsync
import kotlinx.android.synthetic.main.activity_submissions_search.*

class SubmissionsSearchActivity : AppCompatActivity() {

    private val client by lazy { TestApplication.instance.getClient() }

    private val submissions = ArrayList<Submission>()
    private val controller by lazy {
        SubmissionController(object : SubmissionController.SubmissionCallback {

            override fun onUpvoteClick(index: Int) {

                doAsync(doWork = {
                    val submission = submissions[index]
                    client?.contributions?.vote(Vote.UPVOTE, submission)
                })
            }

            override fun onNoneClick(index: Int) {

                doAsync(doWork = {
                    val submission = submissions[index]
                    client?.contributions?.vote(Vote.NONE, submission)
                })
            }

            override fun onDownClick(index: Int) {

                doAsync(doWork = {
                    val submission = submissions[index]
                    client?.contributions?.vote(Vote.DOWNVOTE, submission)
                })
            }

            override fun onSaveClick(index: Int) {

                doAsync(doWork = {
                    val submission = submissions[index]
                    client?.contributions?.save(!submission.isSaved, submission)
                })
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submissions_search)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        list.setHasFixedSize(true)
        list.setController(controller)

        search_bttn.setOnClickListener {

            val subreddit = subreddit.text.toString().trim()
            val query = query.text.toString().trim()

            doAsync(doWork = {

                val fetcher = client?.submissionsSearch(subreddit, query)
                submissions.addAll(fetcher?.fetchNext() ?: listOf())
            }, onPost = {

                controller.setSubmission(submissions)
            })
        }
    }
}